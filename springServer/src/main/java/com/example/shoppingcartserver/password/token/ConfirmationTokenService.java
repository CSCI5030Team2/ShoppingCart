package com.example.shoppingcartserver.password.token;

import com.example.shoppingcartserver.appuser.AppUserRepository;
import com.example.shoppingcartserver.password.ForgotPasswordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author vivek
 */

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final AppUserRepository appUserRepository;

    public void saveConfirmationToken(ConfirmationToken token)
    {
        confirmationTokenRepository.save(token);
    }
    public Optional<ConfirmationToken> getToken(String token)
    {
        return confirmationTokenRepository.findByToken(token);
    }

    public void setConfirmedTime(String token) {
        confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    private boolean isExpired(ConfirmationToken confirmationToken) {
        return confirmationToken.getExpireTime().isBefore(LocalDateTime.now());
    }

    public String confirm(String token) {
        ConfirmationToken confirmationToken;
        if(getToken(token).isPresent()) {
            confirmationToken = getToken(token).get();
            if(isExpired(confirmationToken)) {
                return "Link expired, try again.";
            } else if(appUserRepository.findById(confirmationToken.getId()).isPresent()) {
                appUserRepository.findById(confirmationToken.getId()).get().setPassword("");
                setConfirmedTime(token);
                return "Password Changed.";
            }
        }
        return "User not found";
    }

}
