package com.example.shoppingcartserver.registration.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author aiden
 */

@AllArgsConstructor
@Getter
@ToString
public class AdminRegistrationRequest {

    /*
     * request body
     * looks like a json {"" : ""  ... }
     */

    private final String firstName;
    private final String lastName;
    private final String password;
    private final String email;
    private final String referEmail;
    private final String token;
}
