package com.example.shoppingcartserver.password;

import com.example.shoppingcartserver.appuser.AppUser;
import com.example.shoppingcartserver.appuser.AppUserRepository;
import com.example.shoppingcartserver.login.LoginTokenRepository;
import com.example.shoppingcartserver.login.token.LoginToken;
import com.example.shoppingcartserver.password.request.ResetPasswordRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author aiden
 */
@Service
@AllArgsConstructor
public class ResetPasswordService {

    private final AppUserRepository appUserRepository;
    private final LoginTokenRepository loginTokenRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String resetPassword(ResetPasswordRequest resetPasswordRequest) {

        Optional<AppUser> optionalAppUser = appUserRepository.findByEmail(resetPasswordRequest.getEmail());

        if(optionalAppUser.isEmpty())
        {
            return "User do not exist";
        }
        AppUser appUser = optionalAppUser.get();
        if(!bCryptPasswordEncoder.matches(resetPasswordRequest.getOldPassword(),appUser.getPassword()))
        {
            return "Incorrect Password";
        }
        if(resetPasswordRequest.getOldPassword().equals(resetPasswordRequest.getNewPassword()))
        {
            return "Same password, nothing to do";
        }
        else
        {
            appUser.setPassword(bCryptPasswordEncoder.encode(resetPasswordRequest.getNewPassword()));
            appUserRepository.save(appUser);
            Optional<LoginToken> optionalLoginToken = loginTokenRepository.findByAppUser(appUser);

            //log that person out
            optionalLoginToken.ifPresent(loginTokenRepository::delete);


            return "Password reset success";
        }

    }
}
