package com.example.shoppingcartserver.cart.request;

import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author aiden, vivek
 */
@AllArgsConstructor
@ToString
@Getter
@Setter
public class AddToCartRequest {
    private final String itemId;
    private final String buyerEmail;
    private final String token;
    private final String itemName;
    private final Integer quantity;
    private String token;
}
