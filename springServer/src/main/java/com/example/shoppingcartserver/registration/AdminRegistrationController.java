package com.example.shoppingcartserver.registration;

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
    public String register(@RequestBody AdminRegistrationRequest request)
    {
        return registrationService.registerAdmin(request);
    }
}
