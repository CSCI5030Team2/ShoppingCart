package com.example.shoppingcartserver.password;

import com.example.shoppingcartserver.appuser.AppUserRepository;
import com.example.shoppingcartserver.login.controller.LoginController;
import com.example.shoppingcartserver.login.request.CheckStateRequest;
import com.example.shoppingcartserver.login.request.LoginRequest;
import com.example.shoppingcartserver.password.controller.ResetPasswordController;
import com.example.shoppingcartserver.password.request.ResetPasswordRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import javax.security.auth.login.CredentialException;
import javax.security.auth.login.CredentialExpiredException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author vivek
 */

@SpringBootTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ResetPasswordServiceTest {

    @Autowired
    private ResetPasswordController resetPasswordController;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private LoginController loginController;

    private final String email = "user@shoppingcart.com";
    private final String password = "1q2w3e4r";

    @Order(1)
    @Test
    void checkDB () {
        Assertions.assertNotNull(loginController);
        assertTrue(appUserRepository.findByEmail(email).isPresent());
    }

    @Order(2)
    @Test
    public void testResetPassword() throws CredentialException {

        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest(
                email,
                password,
                "a1234567"
        );

        assertDoesNotThrow(()-> resetPasswordController.resetPassword(resetPasswordRequest));
        assertTrue(appUserRepository.findByEmail(email).isPresent());
    }

    @Order(3)
    @Test
    void testLogin() throws CredentialExpiredException {
        LoginRequest loginRequest = new LoginRequest(
                email,
                "a1234567"
        );
        assertDoesNotThrow(() -> loginController.login(loginRequest));
        CheckStateRequest checkStateRequest = new CheckStateRequest();
        checkStateRequest.setEmail(email);
        assertEquals("OK", loginController.checkState(checkStateRequest));

    }
}