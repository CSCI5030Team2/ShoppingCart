package com.example.shoppingcartserver.password.token.controller;

import com.example.shoppingcartserver.password.request.ForgotPasswordRequest;
import com.example.shoppingcartserver.password.token.ForgotTokenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author vivek
 */

@RequestMapping(path="/changePassword")
@AllArgsConstructor
@CrossOrigin
@RestController
public class ForgotController {

    private ForgotTokenService forgotTokenService;

    @GetMapping
    public String changePassword (@RequestParam String token, ForgotPasswordRequest request) {
        return forgotTokenService.changePassword(token, request);
    }

}
