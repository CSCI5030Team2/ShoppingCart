package com.example.shoppingcartserver.item.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
@AllArgsConstructor
@ToString
@Getter
public class AdminAddItemRequest {
    private final String itemName;
    private final Integer quantity;
    private final Float price;
    //private final String email;
    private final String token;

    //"email": "admin@shoppingcart.com",
    //    "token"
    }