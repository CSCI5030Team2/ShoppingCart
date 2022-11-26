package com.example.shoppingcartserver.item.request;

import lombok.*;

/**
 * @author aiden
 */
@NoArgsConstructor
@ToString
@Data
public class DeleteItemRequest {
    private String itemName;
    private String token;
}
