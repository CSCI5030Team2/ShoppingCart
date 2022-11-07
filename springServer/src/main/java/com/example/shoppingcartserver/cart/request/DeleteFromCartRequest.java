package com.example.shoppingcartserver.cart.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author aiden
 */
@AllArgsConstructor
@ToString
@Getter
public class DeleteFromCartRequest {
    private final String buyerEmail;
    private final String token;
    private final String itemName;
}
