package com.example.shoppingcartserver.cart.request;

import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author vivek
 */
@AllArgsConstructor
@ToString
@Getter
@Setter
public class AddToCartRequest {
    private String buyerEmail;
    private String token;
    private String itemName;
    private Integer quantity;
}
