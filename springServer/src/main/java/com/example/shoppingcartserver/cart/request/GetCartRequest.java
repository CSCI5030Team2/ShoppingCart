package com.example.shoppingcartserver.cart.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class GetCartRequest {
    private String token;
    private final String itemName;
    private final String userEmail;
}