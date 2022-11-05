package com.example.shoppingcartserver.cart.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class UpdateQuantityRequest {
    private final Long id;
    private final Long Quantity;
    private final String buyerEmail;
    private final String itemName;
}
