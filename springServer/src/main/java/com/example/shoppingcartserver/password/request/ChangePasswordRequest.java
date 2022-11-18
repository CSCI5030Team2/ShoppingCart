package com.example.shoppingcartserver.password.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class ChangePasswordRequest {
    private final String email;
    private final String token;
    private final String password;
    private final String confirmPassword;
}
