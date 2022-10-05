package com.example.shoppingcartserver.item.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author aiden
 */
@NoArgsConstructor
@ToString
@Getter
public class DeleteItemRequest {
    private String itemName;
}
