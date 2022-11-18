package com.example.shoppingcartserver.password.token;

import com.example.shoppingcartserver.appuser.AppUserRepository;
import com.example.shoppingcartserver.password.request.ForgotPasswordRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author vivek
 */

@Service
@AllArgsConstructor
public class ForgotTokenService {

    private final ForgotTokenRepository forgotTokenRepository;
    private final AppUserRepository appUserRepository;

    public void saveForgotToken(ForgotToken token)
    {
        forgotTokenRepository.save(token);
    }
    public Optional<ForgotToken> getToken(String token)
    {
        return forgotTokenRepository.findByToken(token);
    }

    public void setTime(String token) {
        forgotTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    private boolean isExpired(ForgotToken forgotToken) {
        return forgotToken.getExpireTime().isBefore(LocalDateTime.now());
    }

    public String changePassword(String token, ForgotPasswordRequest request) {
        ForgotToken forgotToken;
        if(getToken(token).isPresent()) {
            forgotToken = getToken(token).get();
            if(isExpired(forgotToken)) {
                return "Link expired, try again.";
            } else if(appUserRepository.findById(forgotToken.getId()).isPresent()) {
                appUserRepository.findById(forgotToken.getId()).get().setPassword(request.getPassword());
                setTime(token);
                return "Password Changed.";
            }
        }
        return "User not found";
    }

}
