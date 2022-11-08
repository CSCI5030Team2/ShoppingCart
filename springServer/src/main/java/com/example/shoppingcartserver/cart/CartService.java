package com.example.shoppingcartserver.cart;

import com.alibaba.fastjson.JSON;
import com.example.shoppingcartserver.appuser.AppUser;
import com.example.shoppingcartserver.cart.request.*;
import com.example.shoppingcartserver.item.Item;
import com.example.shoppingcartserver.item.ItemRepository;
import com.example.shoppingcartserver.appuser.AppUserRepository;
import com.example.shoppingcartserver.login.LoginRepository;
import com.example.shoppingcartserver.login.token.LoginToken;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author aiden, vivek
 */
@Service
@AllArgsConstructor
public class CartService {

    @Autowired
    private final CartRepository cartRepository;

    @Autowired
    private final ItemRepository itemRepository;

    @Autowired
    private final AppUserRepository appUserRepository;

    @Autowired
    private final LoginRepository loginRepository;

    /**
     * Get all item associated with buyEmail
     *
     * @param request buyerEmail
     * @return JSON containing item
     */
    public String getCart(GetCartRequest request) {
        Optional<CartItem> cartItemList = cartRepository.findByBuyerEmail(request.getUserEmail());
        List<CartItem> cartItemListObj = cartItemList.stream().toList();
        return JSON.toJSONString(cartItemListObj);
    }

    /**
     * For simplicity, clears all items with given buyerEmail
     *
     * @param request buyerEmail
     * @return msg String
     */
    public String checkout(CheckoutCartRequest request) {
        try {
            cartRepository.deleteAllByBuyerEmail(request.getBuyerEmail());
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed Checkout";
        }
        return "Checkout Successful";
    }

    public String addToCart(AddToCartRequest request) {
        AppUser appUser = appUserRepository.findByEmail("someone@something.com").get();
        Optional<LoginToken> optionalLoginToken = loginRepository.findByAppUser(appUser);
        Optional<Item> item = itemRepository.findByItemName(request.getItemName());
        Optional<CartItem> optionalCartItem = cartRepository.findByItemName(request.getItemName());
        // Verify the token of buyer:
        if(optionalLoginToken.isPresent()) {
            LoginToken token = optionalLoginToken.get();
            try {
                token.getExpireTime().isBefore(LocalDateTime.now());
            } catch (Exception e) {
                e.printStackTrace();
                return "Session Expired";
            }
            if (optionalCartItem.isPresent()) {
                List<CartItem> cartItemList = optionalCartItem.stream().toList();
                for(CartItem cartItem : cartItemList)
                {
                    if(cartItem.getItemId().equals(request.getItemId()));
                    {
                        cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
                        cartRepository.save(cartItem);
                        return "Quantity Updated";
                    }
                }
            }
            if (item.isPresent()) {
                Long itemId = item.get().getId();
                String itemName = item.get().getItemName();
                //add to cart
                CartItem cartItem = new CartItem(
                        request.getBuyerEmail(),
                        itemId,
                        request.getQuantity()
                );
                cartRepository.save(cartItem);
                return cartItem.getBuyerEmail() + " -- " + itemName + "Saved to cart ";
            } else {
                return request.getItemName() + " is out of stock.";
            }
        }
        return "User not found";
        }


    public String deleteFromCart(DeleteFromCartRequest request) {
        AppUser appuser = appUserRepository.findByEmail("someone@something.com").get();
        Optional<LoginToken> optionalLoginToken = loginRepository.findByAppUser(appuser);
        Optional<CartItem> cartItem = cartRepository.findByItemName(request.getItemName());
        if(optionalLoginToken.isPresent() || cartItem.isPresent()){
            LoginToken loginToken = optionalLoginToken.get();
        }
        else{

        if(cartItem.isPresent()) {
            Long itemId = cartItem.get().getId();
        cartRepository.deleteItemByName(request.getItemName());
        return "Deleted: " + request.getItemName();
        }
        else {
            return request.getItemName() + "do not exist in your cart";
        }
    }
        return "Needs return statement";

}
}
