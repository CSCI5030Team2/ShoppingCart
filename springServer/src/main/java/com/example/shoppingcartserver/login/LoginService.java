package com.example.shoppingcartserver.login;

import com.example.shoppingcartserver.appuser.AppUser;
import com.example.shoppingcartserver.appuser.AppUserRepository;
import com.example.shoppingcartserver.login.request.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        verifyUser(loginRequest.getEmail(),loginRequest.getPassword());
        //todo handle user login
        return "";
    }


    private String verifyUser(String email, String password)
    {
        boolean userExist = appUserRepository
                .findByEmail(email)
                .isPresent();
        if(!userExist) {
            return "User Do not Exist";
        }
        AppUser appUser = appUserRepository.findByEmail(email).get();

        String givenPassword = bCryptPasswordEncoder.encode(password);
        String actualPassword = "";
        return "";
    }

}
