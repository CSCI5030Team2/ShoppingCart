package com.example.shoppingcartserver.item;

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
public interface ItemRepository extends JpaRepository<Item,Long>{


    /**
     * find all item with the name itemName
     * @param itemName name of the product
     * @return iterable object of matching result
     */
    Optional<Item> findByItemName(String itemName);

    //Optional<Item> setQuantity(String quantity);

    /**
     * delete item by name itemName
     * @param itemName name of the product
     */
    @Transactional
    @Modifying
    @Query("delete from Item i where i.itemName = ?1")
    void deleteItemByName(String itemName);

    /**
     * update item with the name itemName
     * @param itemName name of the product
     * @param price new price
     * @param quantity new quantity
     */
    @Transactional
    @Modifying
    @Query("UPDATE Item i SET i.price = ?2 ,i.quantity = ?3 WHERE i.itemName = ?1"
    )
    void updateItemByName(String itemName, Float price, Integer quantity);

}
