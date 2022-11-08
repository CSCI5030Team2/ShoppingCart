package com.example.shoppingcartserver.cart.request;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class DeleteFromCartRequest {
    private final String userEmail;
    private final String token;
    private String itemId;
    private final String itemName;
    private final Integer quantity;
}
