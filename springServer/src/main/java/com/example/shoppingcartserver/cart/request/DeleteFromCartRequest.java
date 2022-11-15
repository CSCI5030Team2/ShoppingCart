package com.example.shoppingcartserver.cart.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

/**
 * @author aiden, vivek
 */
@AllArgsConstructor
@ToString
@Data
public class DeleteFromCartRequest {
    private final String token;
    private final String itemName;
    private final Integer quantity;
}
