package com.example.shoppingcartserver.password;

import com.example.shoppingcartserver.appuser.AppUser;
import com.example.shoppingcartserver.appuser.AppUserServiceImpl;
import com.example.shoppingcartserver.email.EmailService;
import com.example.shoppingcartserver.password.request.ForgotPasswordRequest;
import com.example.shoppingcartserver.password.request.ForgotResetRequest;
<<<<<<< HEAD
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
=======
import lombok.AllArgsConstructor;
>>>>>>> vivek
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

<<<<<<< HEAD
    private EmailService emailService;
    private AppUserServiceImpl appUserService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String forgotPassword(ForgotPasswordRequest forgotPasswordRequest){
=======
    public EmailService emailService;
    private AppUserServiceImpl appUserService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
>>>>>>> vivek
        try {
            AppUser appUser = appUserService.getAppUserByEmail(forgotPasswordRequest.getEmail());

            if (forgotPasswordRequest.getFirstName().equals(appUser.getFirstName()) &&
<<<<<<< HEAD
                    forgotPasswordRequest.getLastName().equals(appUser.getLastName()))
            {
=======
                    forgotPasswordRequest.getLastName().equals(appUser.getLastName())) {
>>>>>>> vivek
                appUser.setLocked(true);
                appUserService.saveUser(appUser);
                emailService.send(appUser.getEmail(), "Reset password", forgotPasswordRequest.getUrl());
                return "Email sent";
            }
<<<<<<< HEAD
            else
            {
                throw new CredentialException("Name do not match");
            }
        }catch (Exception e) {
=======
            else {
                throw new CredentialException("Name do not match");
            }
        }
        catch (Exception e)
        {
>>>>>>> vivek
            throw new UsernameNotFoundException("Cannot find user with that email");
        }
    }

<<<<<<< HEAD
    public String forgotReset(ForgotResetRequest request) {
        try {
            AppUser appUser = appUserService.getAppUserByEmail(request.getEmail());
            if(appUser.getLocked()) {
                appUser.setLocked(false);
                appUser.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
=======
    public String forgotReset(ForgotResetRequest forgotResetRequest) {
        try {
            AppUser appUser = appUserService.getAppUserByEmail(forgotResetRequest.getEmail());
            if (appUser.getLocked()) {
                appUser.setLocked(false);
                appUser.setPassword(bCryptPasswordEncoder.encode(forgotResetRequest.getEmail()));
>>>>>>> vivek
                appUserService.saveUser(appUser);
                return "Password reset";
            }
            else
            {
                return "You should not use this";
            }
<<<<<<< HEAD

=======
>>>>>>> vivek
        }
        catch (Exception e)
        {
            throw new UsernameNotFoundException("Cannot find user with that email");
        }
    }
}
