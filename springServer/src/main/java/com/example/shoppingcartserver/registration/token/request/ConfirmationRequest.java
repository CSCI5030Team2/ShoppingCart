package com.example.shoppingcartserver.registration.token.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author aiden
 */

@AllArgsConstructor
@Getter
@ToString
public class ConfirmationRequest {

    private final String email;
    private final String token;

}
