package com.example.shoppingcartserver.login;

import com.alibaba.fastjson.JSON;
import com.example.shoppingcartserver.appuser.AppUser;
import com.example.shoppingcartserver.appuser.AppUserServiceImpl;
import com.example.shoppingcartserver.login.request.CheckStateRequest;
import com.example.shoppingcartserver.login.request.LoginRequest;
import com.example.shoppingcartserver.login.request.LogoutRequest;
import com.example.shoppingcartserver.login.token.LoginToken;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import javax.security.auth.login.CredentialExpiredException;
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
    private AppUserServiceImpl appUserService;


    public String login(LoginRequest loginRequest) throws CredentialException {
        AppUser appUser = appUserService.getAppUserByEmail(loginRequest.getEmail());
        ArrayList<String> jsonString = new ArrayList<>();
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

    private boolean passwordValid(String email, String password)
    {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(password,appUserService.getAppUserByEmail(email).getPassword());
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



    public LoginToken loadTokenByEmail(String email) throws CredentialExpiredException {
        AppUser appUser = appUserService.getAppUserByEmail(email);
        Optional<LoginToken> loginToken = loginTokenRepository.findByAppUser(appUser);
        if(loginToken.isPresent())
        {
            return loginToken.get();
        }
        else
        {
            throw new CredentialExpiredException("Login session expired");
        }

    }

    public String checkState(CheckStateRequest checkStateRequest) throws CredentialExpiredException {
        AppUser appUser = appUserService.getAppUserByEmail(checkStateRequest.getEmail());
        LoginToken loginToken = findTokenByAppUer(appUser);
            if (loginToken.getExpireTime().isAfter(LocalDateTime.now())) {
                return "OK";
            } else {
                return "Session Expired";
            }
    }

    public String logout(LogoutRequest logoutRequest) throws CredentialException {

        AppUser appUser = appUserService.getAppUserByEmail(logoutRequest.getEmail());
        Optional<LoginToken> optionalLoginToken = loginTokenRepository.findByAppUser(appUser);
        if(optionalLoginToken.isPresent())
        {
            LoginToken loginToken = optionalLoginToken.get();
            if(loginToken.getToken().equals(logoutRequest.getToken())) {
                loginTokenRepository.delete(loginToken);
            }
            else
            {
                throw new CredentialException("Invalid Token");
            }
        }
        return "Logout Success";

    }

    public LoginToken findTokenByAppUer(AppUser appUser) throws CredentialExpiredException {

        Optional<LoginToken>optionalLoginToken = loginTokenRepository.findByAppUser(appUser);

        if(optionalLoginToken.isPresent())
        {
            return optionalLoginToken.get();
        }
        else
        {
            throw new CredentialExpiredException("Token not found");
        }
    }

    public boolean tokenValid(String token , AppUser appUser)
    {
        Optional<LoginToken> optionalLoginToken = loginTokenRepository.findByToken(token);
        if(optionalLoginToken.isEmpty())
        {
            //did not log in
            return false;
        }
        LoginToken loginToken = optionalLoginToken.get();
//        if(loginToken.getAppUser().getEmail().equals(appUser.getEmail()))
//        {
//            //something wrong, prob hacker
//            return false;
//        }
        return loginToken.getExpireTime().isAfter(LocalDateTime.now());
    }

    public AppUser findAppUserByToken(String token) throws CredentialExpiredException {
        Optional<LoginToken> optionalLoginToken = loginTokenRepository.findByToken(token);
        if(optionalLoginToken.isPresent())
        {
            return optionalLoginToken.get().getAppUser();
        }
        else {
            throw new CredentialExpiredException("session expired");
        }
    }

    public LoginToken findTokenByTokenString(String token) throws CredentialException {
        Optional<LoginToken> optionalLoginToken = loginTokenRepository.findByToken(token);
        if(optionalLoginToken.isPresent())
        {
            return optionalLoginToken.get();
        }
        else {
            throw new CredentialException("Never logged in before");
        }
    }
}
