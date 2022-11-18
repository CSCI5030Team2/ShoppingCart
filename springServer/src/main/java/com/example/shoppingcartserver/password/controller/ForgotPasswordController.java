package com.example.shoppingcartserver.password.controller;


import com.example.shoppingcartserver.password.ForgotPasswordService;
import com.example.shoppingcartserver.password.request.ForgotPasswordRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author vivek
 */

@RestController
@CrossOrigin
@RequestMapping(path = "/forgot-password")
@AllArgsConstructor
public class ForgotPasswordController {

    ForgotPasswordService forgotPasswordService;

    /**
     * @param forgotPasswordRequest email, token, password, confirm password
     */

    @GetMapping
    public void forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest)
    {
        forgotPasswordService.forgotPassword(forgotPasswordRequest);
    }
}
