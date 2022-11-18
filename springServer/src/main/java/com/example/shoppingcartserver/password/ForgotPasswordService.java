package com.example.shoppingcartserver.password;

import com.example.shoppingcartserver.appuser.AppUser;
import com.example.shoppingcartserver.appuser.AppUserRepository;
import com.example.shoppingcartserver.appuser.AppUserServiceImpl;
import com.example.shoppingcartserver.email.EmailService;
import com.example.shoppingcartserver.email.EmailValidator;
import com.example.shoppingcartserver.password.request.ForgotPasswordRequest;
import org.springframework.core.env.Environment;
import com.example.shoppingcartserver.login.LoginTokenRepository;
import com.example.shoppingcartserver.password.token.ForgotToken;
import com.example.shoppingcartserver.password.token.ForgotTokenRepository;
import com.example.shoppingcartserver.password.token.ForgotTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * @author vivek
 */

@AllArgsConstructor
@Service
public class ForgotPasswordService {

    private final AppUserServiceImpl appUserService;
    private final AppUserRepository appUserRepository;
    private final ForgotTokenService forgotTokenService;
    private final ForgotTokenRepository forgotTokenRepository;
    private final EmailValidator emailValidator;
    private final LoginTokenRepository loginTokenRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    Environment environment;
    public String forgotPassword(ForgotPasswordRequest forgotPasswordRequest)  {
        AppUser appUser = appUserService.getAppUserByEmail(forgotPasswordRequest.getEmail());
        resetPasswordEmail(appUser);
        return "Email sent";
    }

    protected String resetPasswordEmail (AppUser appUser) {
        Optional<AppUser> optionalAppUser = appUserRepository.findByEmail(appUser.getEmail());
        if(optionalAppUser.isPresent()) {
            String token = UUID.randomUUID().toString();
            ForgotToken forgotToken = new ForgotToken(
                    token,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(5),
                    appUser
            );
            forgotTokenService.saveForgotToken(forgotToken);
            if(!appUser.getEmail().equals("test@test.com")) {
                EmailService emailService = new EmailService();
                try {
                    emailService.send(appUser.getEmail(),
                            "Reset password",
                            InetAddress.getLocalHost().getHostAddress() + ":" +
                                    environment.getProperty("server.port") + "/user/forgot-password?token=" +
                                    token);
                } catch (UnknownHostException e) {
                    System.err.println("Failed to send email. outlook email account down again?");
                    e.printStackTrace();
                }
            }
            return token;
        }
        return "Email doesn't exist";
    }
}
