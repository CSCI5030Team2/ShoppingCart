package com.example.shoppingcartserver.ads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Data entity for Ads entry
 * @author aiden
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Ads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * A string of ad text
     */
    @Column(nullable = false)
    private String content;
}
