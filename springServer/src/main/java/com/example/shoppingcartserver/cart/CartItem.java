package com.example.shoppingcartserver.cart;


import com.example.shoppingcartserver.appuser.AppUser;
import com.example.shoppingcartserver.item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author aiden, vivek
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "email")
    private String buyerEmail;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Long itemId;
    private Integer quantity;

    public CartItem(String buyerEmail, Long itemId, Integer quantity) {
        this.buyerEmail = buyerEmail;
        this.itemId = itemId;
        this.quantity = quantity;
    }
}

