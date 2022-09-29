package com.example.shoppingcartserver.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author aiden
 */


@AllArgsConstructor
@Getter
@ToString
public class BuyItemRequest {
    private final String itemName;
    private final Integer quantity;
}
