package com.example.shoppingcartserver.registration.token;

import com.example.shoppingcartserver.appuser.AppUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ConfirmationTokenTest {

    ConfirmationToken confirmationToken;

    @Test
    void createEmptyConfirmationToken()
    {
        assertDoesNotThrow(()-> this.confirmationToken = new ConfirmationToken());
    }

    @Test
    void createCompleteConfirmationToken()
    {
        assertDoesNotThrow(
            ()->
                {
                    AppUser appUser = new AppUser();
                    this.confirmationToken = new ConfirmationToken();
                    this.confirmationToken.setAppUser(appUser);
                    this.confirmationToken.setConfirmTime(LocalDateTime.now());
                    this.confirmationToken.setCreateTime(LocalDateTime.now().minusMinutes(1));
                    this.confirmationToken.setToken(UUID.randomUUID().toString());
                    this.confirmationToken.setExpireTime(LocalDateTime.now().plusMinutes(10));
                }
        );
    }

    @AfterEach
    void tearDown()
    {
        this.confirmationToken = null;
    }

}