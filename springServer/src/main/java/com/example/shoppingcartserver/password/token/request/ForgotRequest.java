package com.example.shoppingcartserver.password.token.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author vivek
 */

@AllArgsConstructor
@Getter
@ToString
public class ForgotRequest {

    private final String email;
    private final String token;

}

