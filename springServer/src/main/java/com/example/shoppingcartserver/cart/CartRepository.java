package com.example.shoppingcartserver.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author aiden
 */
@Repository
public interface CartRepository extends JpaRepository<CartItem,Long> {
    /**
     * return all item in the cart of buyerEmail
     * @param buyerEmail  Email address of buyer
     * @return iterable object of matching result
     */
    Optional<CartItem> findByBuyerEmail(String buyerEmail);

    Optional<CartItem> findByItemName(String itemName);


    @Transactional
    @Modifying
    @Query("delete from CartItem i where i.buyerEmail=:buyerEmail")
    void deleteAllByBuyerEmail(String buyerEmail);

    @Transactional
    @Modifying
    @Query("update quantity in CartItem i where i.buyerEmail=buyerEmail")
    void updateQuantityByBuyerEmail(String buyerEmail);

    /**
     * delete item by name itemName
     *
     * @param itemName name of the product
     * @return
     */
    @Transactional
    @Modifying
    @Query("delete from Item i where i.itemName=:itemName")
    Optional<CartItem> deleteItemByName(String itemName);

}
