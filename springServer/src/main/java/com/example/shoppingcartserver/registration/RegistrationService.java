package com.example.shoppingcartserver.registration;

import com.example.shoppingcartserver.appuser.AppUser;
import com.example.shoppingcartserver.appuser.AppUserRole;
import com.example.shoppingcartserver.appuser.AppUserServiceImpl;
import com.example.shoppingcartserver.email.EmailValidator;
import com.example.shoppingcartserver.login.LoginService;
import com.example.shoppingcartserver.login.token.LoginToken;
import com.example.shoppingcartserver.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author aiden
 */
@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserServiceImpl appUserService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;

    @Autowired
    private LoginService loginService;

    public String registerUser(UserRegistrationRequest request) {
        //check email valid
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail)
        {
            throw new IllegalStateException("email not valid");
        }

        //use a method to register new user
        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
    }

    public String registerAdmin(AdminRegistrationRequest request)
    {

        //check email valid
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail)
        {
            throw new IllegalStateException("Email not valid");
        }

        if(!referValid(request.getReferEmail(), request.getToken()))
        {
            throw new IllegalStateException("Referer information not valid, you need to login as an admin");
        }


        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.ADMIN
                )
        );

    }

    private boolean referValid(String email, String token) {
        AppUser referAdmin = appUserService.loadUserByUsername(email);
        LoginToken referAdminLoginToken = loginService.loadTokenByEmail(email);

        boolean referIsAdmin = referAdmin.getAppUserRole().equals(AppUserRole.ADMIN);
        boolean emailMatches = email.equals(referAdmin.getEmail());
        boolean tokenMatches = referAdminLoginToken.getToken().equals(token);
        boolean notExpired = referAdminLoginToken.getExpireTime().isAfter(LocalDateTime.now());

        return referIsAdmin && emailMatches && tokenMatches && notExpired;


    }

}
