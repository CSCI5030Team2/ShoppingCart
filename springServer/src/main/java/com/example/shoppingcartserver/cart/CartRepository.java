package com.example.shoppingcartserver.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author aiden, vivek
 */
@Repository
public interface CartRepository extends JpaRepository<CartItem,Long> {
    /**
     * return all item in the cart of buyerEmail
     * @param buyerEmail  Email address of buyer
     * @return iterable object of matching result
     */

    //@Query( "select o from MyObject o where inventoryId in :ids" )

    //@Query("SELECT u FROM User u WHERE u.status = 1")
    //Collection<User> findAllActiveUsers();

    List<CartItem> findAllByBuyerEmail(String buyerEmail);


    //Optional <CartItem> findByItemName(String itemName);

//    @Transactional
//    @Modifying
//    @Query("delete from CartItem i WHERE i.buyerEmail=buyerEmail")
//    void deleteAllByBuyerEmail(String buyerEmail);
//
//    @Transactional
//    @Modifying
//    @Query("delete from CartItem i WHERE i.itemName=itemName")
//    Optional<CartItem> deleteItemByName(String itemName);
}
