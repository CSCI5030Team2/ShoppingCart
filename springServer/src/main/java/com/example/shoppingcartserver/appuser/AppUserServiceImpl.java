package com.example.shoppingcartserver.appuser;

import com.example.shoppingcartserver.email.EmailService;
import com.example.shoppingcartserver.registration.token.ConfirmationToken;
import com.example.shoppingcartserver.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * @author aiden
 */

@Service
@AllArgsConstructor
public class AppUserServiceImpl {

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService service;

    @Autowired
    Environment environment;


    public AppUser loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> appUser = appUserRepository.findByEmail(email);
        if(appUser.isPresent())
        {
            return appUser.get();
        }
        else
        {
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email));
        }
    }

    public String signUpUser(AppUser appUser)
    {

        Optional<AppUser> optionalAppUser = appUserRepository.findByEmail(appUser.getEmail());

        if(optionalAppUser.isPresent()) {
            return "email already taken";
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        //save user
        appUserRepository.save(appUser);


        if(!appUser.getEnable()) {
            // send confirm token
            // create a token then save use the repo
            String token = UUID.randomUUID().toString();
            ConfirmationToken confirmationToken = new ConfirmationToken(
                    token,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(7),
                    appUser
            );

            service.saveConfirmationToken(confirmationToken);

            EmailService emailService = new EmailService();
            try {
                emailService.send(appUser.getEmail(),
                        "Confirm your account",
                        InetAddress.getLocalHost().getHostAddress() + ":" +
                                environment.getProperty("server.port") + "/user/confirm?token=" +
                                token);
            } catch (UnknownHostException e) {
                System.err.println("Failed to send email. outlook email account down again?");
                e.printStackTrace();
            }

            return token;
        }
        return "Already enabled";
    }

    public boolean isAdmin(String email) {
        Optional<AppUser> optionalAppUser = appUserRepository.findByEmail(email);
        if(optionalAppUser.isPresent())
        {
            return optionalAppUser.get().getAppUserRole() == AppUserRole.ADMIN;
        }
        throw new UsernameNotFoundException(email+ " not found");

    }

    public boolean userDoNotExist(String email)
    {
        return appUserRepository.findByEmail(email).isEmpty();
    }

    public AppUser getAppUserByEmail(String email)
    {
        Optional<AppUser> optionalAppUser = appUserRepository.findByEmail(email);
        if(optionalAppUser.isPresent())
        {
            return optionalAppUser.get();
        }else
        {
            throw new UsernameNotFoundException("Could not find " + email);
        }
    }
}
