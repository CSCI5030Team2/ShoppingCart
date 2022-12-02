package com.example.shoppingcartserver.login.controller;

import com.example.shoppingcartserver.login.LoginService;
import com.example.shoppingcartserver.login.request.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;

/**
 * Admin login module controller layer, ONLY handle REST API calls related to admin login
 * @author aiden
 */
@RestController
@CrossOrigin
@RequestMapping(path = "/admin/login")
@AllArgsConstructor
public class AdminLoginController {
    private LoginService loginService;

    /**
     * Post /admin/login, for admin login
     * @param loginRequest email, password
     * @return token string
     * @throws CredentialException when user DNE or password does not match
     */
    @PostMapping
    public String login(@RequestBody LoginRequest loginRequest) throws CredentialException {
        return loginService.login(loginRequest);
    }
}
