package com.example.shoppingcartserver.registration.controller;

import com.example.shoppingcartserver.registration.request.AdminRegistrationRequest;
import com.example.shoppingcartserver.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author aiden
 *
 * Restration Rest Controller
 */
@RestController
@CrossOrigin
@RequestMapping(path = "/admin/registration")
@AllArgsConstructor
public class AdminRegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    public String registerAdmin(@RequestBody AdminRegistrationRequest request)
    {
        return registrationService.registerAdmin(request);
    }
}
