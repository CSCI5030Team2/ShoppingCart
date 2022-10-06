package com.example.shoppingcartserver.appuser;

import com.example.shoppingcartserver.email.EmailService;
import com.example.shoppingcartserver.registration.token.ConfirmationToken;
import com.example.shoppingcartserver.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author aiden
 */

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService service;
    @Autowired
    Environment environment;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
    }

    public String signUpUser(AppUser appUser)
    {

        boolean userExist = appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();

        if(userExist) {
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        //save user
        appUserRepository.save(appUser);


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

        // TODO: Send email to confirm
        EmailService emailService = new EmailService();


        try {
            emailService.send(appUser.getEmail(),
                    "Confirm your account",
                    InetAddress.getLocalHost().getHostAddress()+":"+
                            environment.getProperty("server.port")+"/user/confirm?token="+
                            token);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return token;
    }

}
