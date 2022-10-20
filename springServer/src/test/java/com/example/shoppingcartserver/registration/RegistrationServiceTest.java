package com.example.shoppingcartserver.registration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @InjectMocks
    RegistrationService registrationService;
    RegistrationRequest request;
    @BeforeEach
    void setup()
    {

    }


    @Test
    void registerEmptyUser()
    {
        //assertThrows(NullPointerException.class, () -> registrationService.register(request));
    }

    @Test
    void registerUser()
    {
        assertDoesNotThrow(() ->
        {
            request = new RegistrationRequest("fn","ln","pw","test@test.com");
            registrationService.register(request);
        }
        );
    }

    @Test
    void registerAdmin()
    {

    }

    @Test
    void registerInvalidEmailUser()
    {

    }

    void buildRequest()
    {
        request = new RegistrationRequest("","","","");
    }


}