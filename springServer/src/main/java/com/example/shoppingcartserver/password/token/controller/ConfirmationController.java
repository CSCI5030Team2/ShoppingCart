package com.example.shoppingcartserver.password.token.controller;

import com.example.shoppingcartserver.password.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author vivek
 */

@RequestMapping(path="/user/confirm")
@AllArgsConstructor
@CrossOrigin
@RestController
public class ConfirmationController {

    private ConfirmationTokenService confirmationTokenService;

    @GetMapping
    public String confirm (@RequestParam String token) {
        return confirmationTokenService.confirm(token);
    }

}
