package com.example.shoppingcartserver.registration.controller;

import com.example.shoppingcartserver.registration.request.AdminRegistrationRequest;
import com.example.shoppingcartserver.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialExpiredException;

/**
 * Admin account registration Rest Controller, not exposed to front-end
 * @author aiden
 */
@RestController
@CrossOrigin
@RequestMapping(path = "/admin/registration")
@AllArgsConstructor
public class AdminRegistrationController {

    private RegistrationService registrationService;

    /**
     * Post /admin/registration, for registration new admin account
     * @param request firstName, lastName, password, email, referEmail, token
     * @return token string
     * @throws CredentialExpiredException when refer email DNE or token does not match
     */
    @PostMapping
    public String registerAdmin(@RequestBody AdminRegistrationRequest request) throws CredentialExpiredException {
        return registrationService.registerAdmin(request);
    }
}
