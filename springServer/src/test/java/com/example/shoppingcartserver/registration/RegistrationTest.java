package com.example.shoppingcartserver.registration;

import com.example.shoppingcartserver.appuser.AppUserRepository;
import com.example.shoppingcartserver.registration.controller.UserRegistrationController;
import com.example.shoppingcartserver.registration.request.UserRegistrationRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class RegistrationTest {

    @Autowired
    private UserRegistrationController userRegistrationController;

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
    @Disabled("Email already taken when testing, skip till db reset")
    void registerUser()
    {
        Random random = new Random();
        String randEmail = "test"+random.nextInt()+"@test.com";
        UserRegistrationRequest request = new UserRegistrationRequest(
                "TestFN",
                "TestLN",
                "PASSWORD",
                randEmail)
                ;

        userRegistrationController.registerUser(request);

        Assertions.assertTrue(appUserRepository.findByEmail(randEmail).isPresent());

    }

    @Test
    @Disabled("Agreed during meeting, do not expose this api to customer")
    void registerAdmin()
    {

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