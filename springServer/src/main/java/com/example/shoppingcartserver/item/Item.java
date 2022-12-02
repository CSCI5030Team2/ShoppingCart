package com.example.shoppingcartserver.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

/**
 * Data entity for Item entry
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

    @Column(nullable = false, unique = true)
    private String itemName;

    @Column(nullable = false)
    @Min(value = 0)
    private Integer quantity;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=Integer.MAX_VALUE, fraction=2)
    @Column(nullable = false)
    private Float price;

    public Item(String itemName, Integer stock, Float price) {
        this.itemName = itemName;
        this.quantity = stock;
        this.price = price;
    }
}
