package com.example.shoppingcartserver.password.request;

<<<<<<< HEAD
import lombok.*;
=======
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
>>>>>>> vivek

/**
 * @author vivek
 */

@ToString
@AllArgsConstructor
<<<<<<< HEAD
@Getter
@Setter
=======
@Data
>>>>>>> vivek
public class ForgotPasswordRequest {
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String url;
}
