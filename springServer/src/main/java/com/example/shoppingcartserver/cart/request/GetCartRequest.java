package com.example.shoppingcartserver.cart.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author aiden, vivek
 */
@AllArgsConstructor
@ToString
@Data
public class GetCartRequest {
    private final String buyerEmail;
    private String token;
}
