package com.example.shoppingcartserver.login.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author aiden
 */
@AllArgsConstructor
@ToString
@Getter
public class LogoutRequest {
    private final String email;
    private final String token;
}
