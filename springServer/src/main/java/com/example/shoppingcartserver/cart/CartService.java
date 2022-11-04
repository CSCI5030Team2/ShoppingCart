package com.example.shoppingcartserver.cart;

import com.alibaba.fastjson.JSON;
import com.example.shoppingcartserver.cart.request.AddToCartRequest;
import com.example.shoppingcartserver.cart.request.GetCartRequest;
import com.example.shoppingcartserver.item.Item;
import com.example.shoppingcartserver.item.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author aiden
 */
@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    private final ItemRepository itemRepository;

    /**
     * Get all item associated with buyEmail
     * @param request buyerEmail
     * @return JSON containing item
     */
    public String getCart(GetCartRequest request)
    {
        Optional<CartItem> cartItemList = cartRepository.findByBuyerEmail(request.getBuyerEmail());
        List<CartItem> cartItemListObj = cartItemList.stream().toList();
        return JSON.toJSONString(cartItemList);
    }

    /**
     * For simplicity, clears all items with given buyerEmail
     * @param request buyerEmail
     * @return msg String
     */
    public String checkout(GetCartRequest request)
    {
        try
        {
        cartRepository.deleteAllByBuyerEmail(request.getBuyerEmail());
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed Checkout";
        }
        return "Checkout Successful";
    }

    public String addToCart(AddToCartRequest request) {

        //todo
        // find that person and get an AppUser instance

        request.getBuyerEmail();
        //1 check this person exist
        request.getToken();
        //2 check if login exist and not expired

        //find item id by name
        Optional<Item> item = itemRepository.findByItemName(request.getItemName());
        if(item.isPresent())
        {
            Long itemId = item.get().getId();
            String itemName = item.get().getItemName();
            //add to cart
            CartItem cartItem = new CartItem(
                    request.getBuyerEmail(),
                    itemId,
                    request.getQuantity()
            );
            //todo if item alreayd exist, just incrse the number
            cartRepository.save(cartItem);
            return "Saved " + cartItem.getBuyerEmail() + " -- " + itemName;

        }
        else
        {
            return request.getItemName() + " do not exist anymore";
        }




    }
    //todo Public String DeleteFromCart
}
