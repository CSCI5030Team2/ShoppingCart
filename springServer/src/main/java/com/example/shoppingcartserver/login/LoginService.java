package com.example.shoppingcartserver.login;

import com.alibaba.fastjson.JSON;
import com.example.shoppingcartserver.appuser.AppUser;
import com.example.shoppingcartserver.appuser.AppUserRepository;
import com.example.shoppingcartserver.login.request.LoginRequest;
import com.example.shoppingcartserver.login.token.LoginToken;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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


    public String login(LoginRequest loginRequest) {
        if(userExist(loginRequest.getEmail()))
        {
            Optional<AppUser> appUserOptional = appUserRepository.findByEmail(loginRequest.getEmail());
            if(appUserOptional.isEmpty()) {
                throw new UsernameNotFoundException("User do not exist");
            }
            AppUser appUser = appUserOptional.get();
            if(passwordValid(loginRequest.getEmail(),loginRequest.getPassword())) {
                Optional<LoginToken> oldToken = loginTokenRepository.findByAppUser(appUser);
                if(oldToken.isPresent())
                {
                    //update token
                    loginTokenRepository.delete(oldToken.get());
                    oldToken.get().setCreateTime(LocalDateTime.now());
                    oldToken.get().setExpireTime(LocalDateTime.now().plusMinutes(60));
                    oldToken.get().setToken(UUID.randomUUID().toString());
                    loginTokenRepository.save(oldToken.get());
                    return oldToken.get().getToken();
                }
                else {
                    //create new token
                    LoginToken loginToken = new LoginToken();
                    loginToken.setToken(UUID.randomUUID().toString());
                    loginToken.setCreateTime(LocalDateTime.now());
                    loginToken.setExpireTime(LocalDateTime.now().plusMinutes(60));
                    loginToken.setAppUser(appUser);
                    loginTokenRepository.save(loginToken);
                    ArrayList<String> jsonString = new ArrayList<>();
                    jsonString.add(loginToken.getToken());
                    jsonString.add(appUser.getAppUserRole().toString());
                    return JSON.toJSONString(jsonString);
                }
            }
            else
            {
                return "Incorrect credential";
            }
        }
        else
        {
            return "User do not exist";
        }
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private boolean passwordValid(String email, String password)
    {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(password,appUserRepository.findByEmail(email).get().getPassword());
    }


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
}
