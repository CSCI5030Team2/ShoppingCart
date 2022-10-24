package com.example.shoppingcartserver.login;

import com.example.shoppingcartserver.appuser.AppUser;
import com.example.shoppingcartserver.appuser.AppUserRepository;
import com.example.shoppingcartserver.login.request.LoginRequest;
import com.example.shoppingcartserver.login.token.LoginToken;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * @author aiden
 */
@Service
@AllArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public String login(LoginRequest loginRequest) {
        if(userExist(loginRequest.getEmail()))
        {
            AppUser appUser = appUserRepository.findByEmail(loginRequest.getEmail()).get();
            if(passwordValid(loginRequest.getEmail(),loginRequest.getPassword())) {
                Optional<LoginToken> oldToken = null;
                //Optional<LoginToken> oldToken = loginRepository.findByAppUserId(appUser.getId());
                if(oldToken.isPresent())
                {
                    //update token
                    oldToken.get().setCreateTime(LocalDateTime.now());
                    oldToken.get().setExpireTime(LocalDateTime.now().plusMinutes(60));
                    oldToken.get().setToken(UUID.randomUUID().toString());
                    loginRepository.save(oldToken.get());
                    return oldToken.get().getToken();
                }
                else {
                    //create new token
                    LoginToken loginToken = new LoginToken();
                    loginToken.setToken(UUID.randomUUID().toString());
                    loginToken.setCreateTime(LocalDateTime.now());
                    loginToken.setExpireTime(LocalDateTime.now().plusMinutes(60));
                    loginRepository.save(loginToken);
                    return loginToken.getToken();
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
        return Objects.equals(bCryptPasswordEncoder.encode(password), appUserRepository.findByEmail(email).get().getPassword());
    }


    private boolean userExist(String email) {
        return appUserRepository
                .findByEmail(email)
                .isPresent();
    }

}
