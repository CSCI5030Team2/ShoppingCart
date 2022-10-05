package com.example.shoppingcartserver.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author aiden
 */

@AllArgsConstructor
@Getter
@ToString
public class RegistrationRequest {

    /*
     * request body
     * looks like a json {"" : ""  ... }
     */

    private final String firstName;
    private final String lastName;
    private final String password;
    private final String email;
}
