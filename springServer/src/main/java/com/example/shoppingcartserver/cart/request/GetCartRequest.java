package com.example.shoppingcartserver.cart.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author aiden
 */
@AllArgsConstructor
@Getter
@ToString
public class GetCartRequest {
    private final String buyerEmail;
    private final String token;
}
