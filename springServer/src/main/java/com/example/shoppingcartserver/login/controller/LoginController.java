package com.example.shoppingcartserver.login.controller;

import com.example.shoppingcartserver.login.LoginService;
import com.example.shoppingcartserver.login.request.CheckStateRequest;
import com.example.shoppingcartserver.login.request.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;
import javax.security.auth.login.CredentialExpiredException;

/**
 * @author aiden
 */
@RestController
@CrossOrigin
@RequestMapping(path = "/login")
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;

    /**
     * @param loginRequest email and password
     * @return login token, user role
     */
    @PostMapping
    public String login(@RequestBody LoginRequest loginRequest) throws CredentialException {
        return loginService.login(loginRequest);
    }


    /**
     * @param checkStateRequest email
     * @return status message
     */
    @GetMapping
    public String checkState(@RequestBody CheckStateRequest checkStateRequest) throws CredentialExpiredException {
        return loginService.checkState(checkStateRequest);
    }

}
