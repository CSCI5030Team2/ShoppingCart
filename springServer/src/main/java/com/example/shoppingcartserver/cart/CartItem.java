package com.example.shoppingcartserver.cart;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author aiden
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String buyerEmail;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private Integer quantity;


    public CartItem(String buyerEmail, Long itemId, Integer quantity) {
        this.buyerEmail = buyerEmail;
        this.itemId = itemId;
        this.quantity = quantity;
    }
}

