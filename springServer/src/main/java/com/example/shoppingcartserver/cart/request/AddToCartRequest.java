package com.example.shoppingcartserver.cart.request;

import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author aiden, vivek
 */
@AllArgsConstructor
@ToString
@Data
public class AddToCartRequest {
    private final String itemName;
    private final Integer quantity;
    private String token;
}