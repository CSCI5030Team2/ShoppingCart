package com.example.shoppingcartserver.login.controller;

import com.example.shoppingcartserver.login.LoginService;
import com.example.shoppingcartserver.login.request.LogoutRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialExpiredException;


/**
 * @author aiden
 */
@RestController
@CrossOrigin
@RequestMapping(path = "/logout")
@AllArgsConstructor
public class LogoutController {

    private final LoginService loginService;

    @PostMapping
    public String logout(@RequestBody LogoutRequest logoutRequest) throws CredentialExpiredException {
        return loginService.logout(logoutRequest);
    }
}
