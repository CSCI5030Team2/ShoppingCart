package com.example.shoppingcartserver.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @author aiden, vivek
 */

@Repository
public interface CartRepository extends JpaRepository<CartItem,Long> {
    /**
     * return all items in the cart of buyerEmail
     * @param buyerEmail  Email address of buyer
     * @return iterable object of matching result
     */

    List<CartItem> findAllByBuyerEmail(String buyerEmail);


    /**
     * delete all items in the cart of buyerEmail
     * @param buyerEmail  Email address of buyer
     * @return iterable object of matching result
     */
    @Transactional
    @Modifying
    @Query("delete from CartItem i WHERE i.buyerEmail = ?1")
    void deleteAllByBuyerEmail(String buyerEmail);
}
