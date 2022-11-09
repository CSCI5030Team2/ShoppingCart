package com.example.shoppingcartserver.password.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author aiden
 */
@AllArgsConstructor
@ToString
@Getter
public class ResetPasswordRequest {
    private final String email;
    private final String oldPassword;
    private final String newPassword;
}
