package com.example.shoppingcartserver.registration;

import com.example.shoppingcartserver.appuser.AppUser;
import com.example.shoppingcartserver.appuser.AppUserRepository;
import com.example.shoppingcartserver.registration.controller.UserRegistrationController;
import com.example.shoppingcartserver.registration.request.UserRegistrationRequest;
import com.example.shoppingcartserver.registration.token.ConfirmationToken;
import com.example.shoppingcartserver.registration.token.ConfirmationTokenRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Random;

@SpringBootTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RegistrationTest {

    @Autowired
    private UserRegistrationController userRegistrationController;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(userRegistrationController);
    }
    @Test
    void registerEmptyUser()
    {
        UserRegistrationRequest request = new UserRegistrationRequest(
                null,
                null,
                null,
                null);
        Assertions.assertThrows(NullPointerException.class, () ->
                userRegistrationController.registerUser(request));
    }

    @Test
    void registerUser()
    {
        String email = "test@test.com";
        UserRegistrationRequest request = new UserRegistrationRequest(
                "TestFN",
                "TestLN",
                "PASSWORD",
                email)
                ;

        userRegistrationController.registerUser(request);

        Optional<AppUser> optionalAppUser = appUserRepository.findByEmail(email);
        Assertions.assertTrue(optionalAppUser.isPresent());
        AppUser appUser = optionalAppUser.get();
        Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenRepository.findByAppUser(appUser);
        Assertions.assertTrue(optionalConfirmationToken.isPresent());

        Assertions.assertDoesNotThrow(() -> confirmationTokenRepository.deleteByAppUser(appUser));
        Assertions.assertDoesNotThrow(() -> appUserRepository.deleteByEmail(email));

    }

    @Test
    void registerInvalidEmailUser()
    {
        UserRegistrationRequest request = new UserRegistrationRequest(
                "TestFN",
                "TestLN",
                "PASSWORD",
                "notAnEmail");

        Assertions.assertThrows(IllegalStateException.class , () ->
                userRegistrationController.registerUser(request));
    }



}