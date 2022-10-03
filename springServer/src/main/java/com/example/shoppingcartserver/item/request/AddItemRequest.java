package com.example.shoppingcartserver.item.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author aiden
 */
@AllArgsConstructor
@ToString
@Getter
public class AddItemRequest {
    private final String itemName;
    private final Integer quantity;
    private final Float price;
}
