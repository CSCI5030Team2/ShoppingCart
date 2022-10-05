package com.example.shoppingcartserver.registration.token.controller;

import com.example.shoppingcartserver.registration.token.ConfirmationTokenService;
import com.example.shoppingcartserver.registration.token.request.ConfirmationRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aiden
 */
@RestController
@RequestMapping(path="/user/confirm")
@AllArgsConstructor
public class ConfirmationController {

    private ConfirmationTokenService confirmationTokenService;

    @PostMapping
    public String confirm (@RequestBody ConfirmationRequest request)
    {
        return confirmationTokenService.confirm(request);
    }
}
