package com.example.shoppingcartserver.password.request;

import lombok.*;

/**
 * @author vivek
 */

@ToString
@AllArgsConstructor
@Getter
@Setter
public class ForgotPasswordRequest {
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String url;
}
