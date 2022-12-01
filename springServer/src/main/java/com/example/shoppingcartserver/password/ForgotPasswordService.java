package com.example.shoppingcartserver.password;

import com.example.shoppingcartserver.appuser.AppUser;
import com.example.shoppingcartserver.appuser.AppUserServiceImpl;
import com.example.shoppingcartserver.email.EmailService;
import com.example.shoppingcartserver.password.request.ForgotPasswordRequest;
import com.example.shoppingcartserver.password.request.ForgotResetRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;

/**
 * @author vivek, aiden
 */

@AllArgsConstructor
@Service
public class ForgotPasswordService {

    public EmailService emailService;
    private AppUserServiceImpl appUserService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     * for forgot password(send reset email)
     * @param forgotPasswordRequest
     * @return String of exception with related information
     */
    public String forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        try {
            AppUser appUser = appUserService.getAppUserByEmail(forgotPasswordRequest.getEmail());

            if (forgotPasswordRequest.getFirstName().equals(appUser.getFirstName()) &&
                    forgotPasswordRequest.getLastName().equals(appUser.getLastName())) {
                appUser.setLocked(true);
                appUserService.saveUser(appUser);
                emailService.send(appUser.getEmail(), "Reset password", forgotPasswordRequest.getUrl());
                return "Email sent";
            }
            else {
                throw new CredentialException("Name do not match");
            }
        }
        catch (Exception e)
        {
            throw new UsernameNotFoundException("Cannot find user with that email");
        }
    }

    /**
     * for reset password(reset user password)
     * @param forgotResetRequest
     * @return String of exception with related information
     */
    public String forgotReset(ForgotResetRequest forgotResetRequest) {
        try {
            AppUser appUser = appUserService.getAppUserByEmail(forgotResetRequest.getEmail());
            if (appUser.getLocked()) {
                appUser.setLocked(false);
                appUser.setPassword(bCryptPasswordEncoder.encode(forgotResetRequest.getPassword()));
                appUserService.saveUser(appUser);
                return "Password reset";
            }
            else
            {
                return "You should not use this";
            }
        }
        catch (Exception e)
        {
            throw new UsernameNotFoundException("Cannot find user with that email");
        }
    }
}
