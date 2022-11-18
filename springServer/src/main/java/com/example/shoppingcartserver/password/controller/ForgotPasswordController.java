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

    private ForgotPasswordService forgotPasswordService;

    /**
     * @param request email, token, password, confirm password
     * @return
     */

    @PostMapping
    public String forgotPassword(@RequestBody ForgotPasswordRequest request)
    {
        return forgotPasswordService.forgotPassword(request);
    }
}
