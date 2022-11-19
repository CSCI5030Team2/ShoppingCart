package com.example.shoppingcartserver.password.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author vivek
 */

@AllArgsConstructor
@ToString
@Data
public class ForgotResetRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
}
