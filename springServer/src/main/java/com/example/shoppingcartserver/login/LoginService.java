package com.example.shoppingcartserver.login;

import com.alibaba.fastjson.JSON;
import com.example.shoppingcartserver.appuser.AppUser;
import com.example.shoppingcartserver.appuser.AppUserRepository;
import com.example.shoppingcartserver.email.EmailValidator;
import com.example.shoppingcartserver.login.request.CheckStateRequest;
import com.example.shoppingcartserver.login.request.LoginRequest;
import com.example.shoppingcartserver.login.request.LogoutRequest;
import com.example.shoppingcartserver.login.token.LoginToken;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

/**
 * @author aiden
 */
@Service
@AllArgsConstructor
public class LoginService {

    private final LoginTokenRepository loginTokenRepository;
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public String login(LoginRequest loginRequest) throws CredentialException {
        if(userExist(loginRequest.getEmail()))
        {
            ArrayList<String> jsonString = new ArrayList<>();
            Optional<AppUser> appUserOptional = appUserRepository.findByEmail(loginRequest.getEmail());
            if(appUserOptional.isEmpty()) {
                throw new UsernameNotFoundException("User do not exist");
            }
            AppUser appUser = appUserOptional.get();
            if(passwordValid(loginRequest.getEmail(),loginRequest.getPassword())) {
                LoginToken oldToken = getToken(loginTokenRepository.findByAppUser(appUser));
                if(oldToken!=null)
                {
                    //update token
                    loginTokenRepository.delete(oldToken);
                    oldToken.setCreateTime(LocalDateTime.now());
                    oldToken.setExpireTime(LocalDateTime.now().plusMinutes(60));
                    oldToken.setToken(UUID.randomUUID().toString());
                    loginTokenRepository.save(oldToken);
                    jsonString.add(oldToken.getToken());
                }
                else {
                    //create new token
                    LoginToken loginToken = new LoginToken();
                    loginToken.setToken(UUID.randomUUID().toString());
                    loginToken.setCreateTime(LocalDateTime.now());
                    loginToken.setExpireTime(LocalDateTime.now().plusMinutes(60));
                    loginToken.setAppUser(appUser);
                    loginTokenRepository.save(loginToken);
                    jsonString.add(loginToken.getToken());
                }
                jsonString.add(appUser.getAppUserRole().toString());
                return JSON.toJSONString(jsonString);
            }
            else
            {
                throw new CredentialException("Incorrect credential");
            }
        }
        else
        {
            throw new UsernameNotFoundException("User do not exist");
        }
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private boolean passwordValid(String email, String password)
    {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(password,appUserRepository.findByEmail(email).get().getPassword());
    }

    private LoginToken getToken(Optional<LoginToken> optionalLoginToken)
    {
        if(optionalLoginToken.isEmpty())
        {
            return null;
        }
        else
        {
            return optionalLoginToken.get();
        }
    }


    /**
     * @param email email of AppUser
     * @return boolean user exist
     */
    private boolean userExist(String email) {
        return appUserRepository
                .findByEmail(email)
                .isPresent();
    }

    public LoginToken loadTokenByEmail(String email) {
        Optional<AppUser> appUser = appUserRepository.findByEmail(email);
        if(appUser.isPresent()) {
            Optional<LoginToken> loginToken = loginTokenRepository.findByAppUser(appUser.get());
            if(loginToken.isPresent())
            {
                return loginToken.get();
            }
            else
            {
                throw new IllegalStateException("Login session expired");
            }
        }
        else
        {
            throw new UsernameNotFoundException("User do not exist");
        }
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public String checkState(CheckStateRequest checkStateRequest) {
        String email = checkStateRequest.getEmail();
        EmailValidator emailValidator= new EmailValidator();
        if(!emailValidator.test(email))
        {
            return "Invalid email";
        }
        if(userExist(email)) {
            AppUser appUser = appUserRepository.findByEmail(email).get();
            Optional<LoginToken> loginToken = loginTokenRepository.findByAppUser(appUser);
            if (loginToken.isPresent()) {
                if (loginToken.get().getExpireTime().isAfter(LocalDateTime.now())) {
                    return "OK";
                } else {
                    return "Session Expired";
                }
            }

        }
        else {
            return ("Login session do not exist");
        }
        throw new RuntimeException("Your email so bad, the server wend down ");
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public String logout(LogoutRequest logoutRequest) {

        if(userExist(logoutRequest.getEmail()))
        {
            AppUser appUser = appUserRepository.findByEmail(logoutRequest.getEmail()).get();
            Optional<LoginToken> optionalLoginToken = loginTokenRepository.findByAppUser(appUser);
            if(optionalLoginToken.isPresent())
            {
                loginTokenRepository.delete(optionalLoginToken.get());
                return "Logout Success";
            }
            else
            {
                return "Session expired, no need to logout";
            }
        }
        else
        {
            return "User do not exist";
        }


    }
}
