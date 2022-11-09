package com.example.shoppingcartserver.cart;


import com.example.shoppingcartserver.item.Item;
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
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String buyerEmail;

    @ManyToOne
    @JoinColumn( nullable = false)
    private Item item;

    private Integer quantity;

}

