package com.example.shoppingcartserver.password.controller;

import com.example.shoppingcartserver.password.ResetPasswordService;
import com.example.shoppingcartserver.password.request.ResetPasswordRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path = "/reset-password")
@AllArgsConstructor
public class ResetPasswordController {


    ResetPasswordService resetPasswordService;

    /**
     * @param resetPasswordRequest email, old password, new password
     * @return status message
     */
    @PostMapping
    public String resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest)
    {
        return resetPasswordService.resetPassword(resetPasswordRequest);
    }
}
