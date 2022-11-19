package com.example.shoppingcartserver.password.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author vivek
 */

@Data
@ToString
@AllArgsConstructor
public class ForgotPasswordRequest {
    private final String email;
    private final String url;
}
