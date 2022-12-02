package com.example.shoppingcartserver.registration.token.controller;

import com.example.shoppingcartserver.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author aiden
 */
@RestController
@CrossOrigin
@RequestMapping(path="/user/confirm")
@AllArgsConstructor
public class ConfirmationController {

    private ConfirmationTokenService confirmationTokenService;

    /**
     * Get /user/confirm
     * For user account confirmation, should only be access via email link
     * @param token token string
     * @return status string
     */
    @GetMapping
    public String confirm (@RequestParam String token)
    {
        return confirmationTokenService.confirm(token);
    }
}
