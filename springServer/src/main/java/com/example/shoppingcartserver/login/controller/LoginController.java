package com.example.shoppingcartserver.login.controller;

import com.example.shoppingcartserver.login.LoginService;
import com.example.shoppingcartserver.login.request.CheckStateRequest;
import com.example.shoppingcartserver.login.request.LoginRequest;
import com.example.shoppingcartserver.login.request.LogoutRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;
import javax.security.auth.login.CredentialExpiredException;

/**
 * User login module controller layer, handles REST API calls related to general user login
 * @author aiden
 */
@RestController
@CrossOrigin
@RequestMapping(path = "/login")
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;

    /**
     * Post /login, for user login
     * @param loginRequest email and password
     * @return login token, user role
     */
    @PostMapping
    public String login(@RequestBody LoginRequest loginRequest) throws CredentialException {
        return loginService.login(loginRequest);
    }


    /**
     * Get login/ check if the user is logged in
     * legacy API, not used by frontend
     * @param checkStateRequest email
     * @return status message
     */
    @GetMapping
    public String checkState(@RequestBody CheckStateRequest checkStateRequest) throws CredentialExpiredException {
        return loginService.checkState(checkStateRequest);
    }

    /**
     * Del /login, for logout user/admin
     * @param logoutRequest email, token
     * @return status string
     * @throws CredentialException when email DNE, or token does not match
     */
    @DeleteMapping
    public String logout(@RequestBody LogoutRequest logoutRequest) throws CredentialException {
        return loginService.logout(logoutRequest);
    }

}
