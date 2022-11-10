package com.example.shoppingcartserver.cart.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

/**
 * @author vivek
 */
@AllArgsConstructor
@ToString
@Data
public class CheckoutCartRequest {
    private final String itemName;
    private final String itemId;
    private final String buyerEmail;
    private String token;
}