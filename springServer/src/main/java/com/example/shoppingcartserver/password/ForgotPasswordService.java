package com.example.shoppingcartserver.password;

import com.example.shoppingcartserver.appuser.AppUser;
import com.example.shoppingcartserver.appuser.AppUserRepository;
import com.example.shoppingcartserver.appuser.AppUserServiceImpl;
import com.example.shoppingcartserver.email.EmailService;
import com.example.shoppingcartserver.email.EmailValidator;
import com.example.shoppingcartserver.password.request.ForgotPasswordRequest;
import com.example.shoppingcartserver.password.request.ForgotResetRequest;
import org.springframework.core.env.Environment;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author vivek
 */

@AllArgsConstructor
@Service
public class ForgotPasswordService {

    private final AppUserServiceImpl appUserService;
    private final EmailService emailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    Environment environment;

    public String forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        AppUser appUser = appUserService.getAppUserByEmail(forgotPasswordRequest.getEmail());
        appUser.setLocked(true);
        emailService.send(appUser.getEmail(), "Reset password", forgotPasswordRequest.getUrl());
        return "Email sent";
    }

    public String forgotReset(ForgotResetRequest request) {
        AppUser appUser1 = appUserService.getAppUserByEmail(request.getEmail());
        if (request.getFirstName() == appUser1.getFirstName()) {
            if (request.getLastName() == appUser1.getLastName()) {
                if (request.getEmail() == appUser1.getEmail()) {
                    String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
                    appUser1.setPassword(encodedPassword);
                } else return "Details doesn't match";
            }
        }
        return "Password changed";
    }
}
