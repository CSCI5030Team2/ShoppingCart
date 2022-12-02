package com.example.shoppingcartserver.registration.controller;

import com.example.shoppingcartserver.registration.RegistrationService;
import com.example.shoppingcartserver.registration.request.UserRegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author aiden
 *
 * Restration Rest Controller
 */
@RestController
@CrossOrigin
@RequestMapping(path = "/user/registration")
@AllArgsConstructor
public class UserRegistrationController {

    private RegistrationService registrationService;

    /**
     * Post /user/registration, for registering new user account
     * @param request firstName, lastName, email, password
     * @return token string
     */
    @PostMapping
    public String registerUser(@RequestBody UserRegistrationRequest request)
    {
        return registrationService.registerUser(request);
    }
}
