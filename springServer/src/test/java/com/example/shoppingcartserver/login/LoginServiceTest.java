package com.example.shoppingcartserver.login;

import com.example.shoppingcartserver.appuser.AppUserRepository;
import com.example.shoppingcartserver.login.controller.LoginController;
import com.example.shoppingcartserver.login.controller.LogoutController;
import com.example.shoppingcartserver.login.request.CheckStateRequest;
import com.example.shoppingcartserver.login.request.LoginRequest;
import com.example.shoppingcartserver.login.request.LogoutRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import javax.security.auth.login.CredentialException;
import javax.security.auth.login.CredentialExpiredException;

import static org.junit.jupiter.api.Assertions.*;


/**
 * LoginService integration test
 * @author aiden
 *
 * the best way to test our project  just add @SpringBootTest to instantiate all layers
 * then add @AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
 * to give it access to database
 */
@SpringBootTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class LoginServiceTest {

    @Autowired
    LoginController loginController;

    @Autowired
    LogoutController logoutController;

    @Autowired
    LoginTokenRepository loginTokenRepository;

    @Autowired
    AppUserRepository appUserRepository;

    private final String EMAIL = "admin@shoppingcart.com";

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(loginController);
    }

    @Test
    public void wrongPassword()  {
        LoginRequest loginRequest = new LoginRequest(
                EMAIL,
                "wrongPassword");
        assertThrows(CredentialException.class,() -> loginController.login(loginRequest));
    }

    @Test
    public void successLoginTest() throws CredentialExpiredException {
        LoginRequest loginRequest = new LoginRequest(
                EMAIL,
                "a123456");
        assertDoesNotThrow(() -> loginController.login(loginRequest));
        CheckStateRequest checkStateRequest = new CheckStateRequest();
        checkStateRequest.setEmail(EMAIL);
        assertEquals("OK", loginController.checkState(checkStateRequest));
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void successLogoutTest() throws CredentialExpiredException {
        LoginRequest loginRequest = new LoginRequest(
                EMAIL,
                "a123456");
        assertDoesNotThrow(() -> loginController.login(loginRequest));
        CheckStateRequest checkStateRequest = new CheckStateRequest();
        checkStateRequest.setEmail(EMAIL);
        assertEquals("OK", loginController.checkState(checkStateRequest));

        LogoutRequest logoutRequest = new LogoutRequest(
                EMAIL,
                loginTokenRepository.findByAppUser(
                        appUserRepository.findByEmail(EMAIL).get()
                ).get().getToken()
        );
        assertEquals("Logout Success" , logoutController.logout(logoutRequest));

    }


}