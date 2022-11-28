package com.example.shoppingcartserver.password.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author vivek
 */

@ToString
@AllArgsConstructor
@Data
public class ForgotPasswordRequest {
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String url;
}
