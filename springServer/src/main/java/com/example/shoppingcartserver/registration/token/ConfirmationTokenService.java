package com.example.shoppingcartserver.registration.token;

import com.example.shoppingcartserver.appuser.AppUser;
import com.example.shoppingcartserver.appuser.AppUserRepository;
import com.example.shoppingcartserver.registration.token.request.ConfirmationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author aiden
 */

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    //this saves confirmation tokens

    private final ConfirmationTokenRepository confirmationTokenRepository;

    private final AppUserRepository appUserRepository;

    public void saveConfirmationToken(ConfirmationToken token){
        confirmationTokenRepository.save(token);
    }
    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public void setConfirmedTime(String token) {
        confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    private boolean isExpired(ConfirmationToken confirmationToken)
    {
        return confirmationToken.getExpireTime().isBefore(LocalDateTime.now());
    }

    public String confirm(String token) {
        ConfirmationToken confirmationToken;
        if(getToken(token).isPresent()) {
            confirmationToken = getToken(token).get();
            if (isExpired(confirmationToken)) {
                return "Account expired, register again.";
                //TODO delete old record and token? not yet decided.
            } else if (appUserRepository.findById(confirmationToken.getId()).isPresent()) {
                //Enable user
                appUserRepository.findById(confirmationToken.getId()).get().setEnable(true);
                setConfirmedTime(token);
                return "Account verified.";
            }
        }
        return "Confirmation failed. Token do not exist.";
    }
}
