package com.example.shoppingcartserver.cart.request;

import lombok.*;

/**
 * @author aiden, vivek
 */
@AllArgsConstructor
@ToString
@Getter
@NoArgsConstructor
public class GetCartRequest {
    private String token;
}
