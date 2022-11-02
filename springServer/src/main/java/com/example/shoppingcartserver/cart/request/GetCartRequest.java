package com.example.shoppingcartserver.cart.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author vivek
 */
@AllArgsConstructor
@Getter
@ToString
public class GetCartRequest {
    private final String buyerEmail;
}
