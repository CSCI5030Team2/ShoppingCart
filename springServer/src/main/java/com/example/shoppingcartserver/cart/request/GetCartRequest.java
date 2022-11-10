package com.example.shoppingcartserver.cart.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

/**
 * @author aiden
 */
@AllArgsConstructor
@ToString
@Data
public class GetCartRequest {
    private final String buyerEmail;
    private String token;
}
