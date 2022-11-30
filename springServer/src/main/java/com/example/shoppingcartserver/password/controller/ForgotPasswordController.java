package com.example.shoppingcartserver.password.controller;

<<<<<<< HEAD

=======
>>>>>>> vivek
import com.example.shoppingcartserver.password.ForgotPasswordService;
import com.example.shoppingcartserver.password.request.ForgotPasswordRequest;
import com.example.shoppingcartserver.password.request.ForgotResetRequest;
import lombok.AllArgsConstructor;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.CredentialException;
=======
import org.springframework.web.bind.annotation.*;
>>>>>>> vivek

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
     * @param request email, firstName, lastName, frontend url
     * @return status string
     */

    @PostMapping
    public String forgotPassword(@RequestBody ForgotPasswordRequest request) {
        return forgotPasswordService.forgotPassword(request);
    }

    @PutMapping
<<<<<<< HEAD
    public String forgetReset(@RequestBody ForgotResetRequest request)
    {
        return forgotPasswordService.forgotReset(request);
    }
=======
    public String forgotReset(@RequestBody ForgotResetRequest request) {
        return forgotPasswordService.forgotReset(request);
    }

>>>>>>> vivek
}
