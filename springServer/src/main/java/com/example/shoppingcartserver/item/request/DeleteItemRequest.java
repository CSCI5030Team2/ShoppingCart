package com.example.shoppingcartserver.item.request;

import lombok.*;

/**
 * @author aiden
 */
@AllArgsConstructor
@ToString
@Getter
public class DeleteItemRequest {
    private String itemName;
    private String token;
}
