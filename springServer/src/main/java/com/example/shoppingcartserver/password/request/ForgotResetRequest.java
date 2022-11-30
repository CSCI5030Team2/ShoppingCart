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
public class ForgotResetRequest {
    private final String email;
    private final String password;
}
