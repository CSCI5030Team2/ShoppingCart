package com.example.shoppingcartserver.login.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * request body of get login request
 * @author aiden
 */
@NoArgsConstructor
@ToString
@Getter
public class CheckStateRequest {
    private String email;
}
