package com.example.shoppingcartserver.cart.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author vivek
 */
@AllArgsConstructor
@ToString
@Getter
public class AddToCartRequest {
    private final String buyerEmail;
    private final String itemName;
    private final Integer quantity;
}
