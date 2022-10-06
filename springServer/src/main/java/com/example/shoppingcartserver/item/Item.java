package com.example.shoppingcartserver.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author aiden
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    private Integer quantity;

    private Float price;

    public Item(String itemName, Integer stock, Float price) {
        this.itemName = itemName;
        this.quantity = stock;
        this.price = price;
    }
}
