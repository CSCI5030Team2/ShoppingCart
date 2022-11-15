package com.example.shoppingcartserver.cart.request;

import lombok.*;

/**
 * @author aiden, vivek
 */
@AllArgsConstructor
@ToString
@Getter
public class GetCartRequest {
    private String token;
}
