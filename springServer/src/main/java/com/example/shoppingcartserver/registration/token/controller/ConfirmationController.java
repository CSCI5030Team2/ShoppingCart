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

    @GetMapping
    public String confirm (@RequestParam String token)
    {
        return confirmationTokenService.confirm(token);
    }
}
