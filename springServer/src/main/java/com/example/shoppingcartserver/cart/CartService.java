package com.example.shoppingcartserver.cart;

import com.alibaba.fastjson.JSON;
import com.example.shoppingcartserver.appuser.AppUser;
import com.example.shoppingcartserver.appuser.AppUserServiceImpl;
import com.example.shoppingcartserver.cart.request.AddToCartRequest;
import com.example.shoppingcartserver.cart.request.DeleteFromCartRequest;
import com.example.shoppingcartserver.cart.request.GetCartRequest;
import com.example.shoppingcartserver.item.Item;
import com.example.shoppingcartserver.item.ItemService;
import com.example.shoppingcartserver.login.LoginService;
import com.example.shoppingcartserver.login.token.LoginToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import javax.security.auth.login.CredentialExpiredException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author aiden
 */
@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    private AppUserServiceImpl appUserService;
    private LoginService loginService;
    private ItemService itemService;

    /**
     * Get all item associated with buyEmail
     * @param request buyerEmail
     * @return JSON containing item
     */
    public String getCart(GetCartRequest request)
    {
        List<CartItem> cartItemList  = cartRepository.findAllByBuyerEmail(request.getBuyerEmail());
        List<CartItem> cartItemListObj = cartItemList.stream().toList();
        return JSON.toJSONString(cartItemListObj);
    }

    /**
     * For simplicity, clears all items with given buyerEmail
     * @param request buyerEmail
     * @return msg String
     */
    public String checkout(GetCartRequest request) throws Exception {
        AppUser appUser = appUserService.getAppUserByEmail(request.getBuyerEmail());

        if(!loginService.tokenValid(request.getToken(),appUser))
        {
            throw new CredentialException("Invalid token");
        }

        List<CartItem> cartItemList = cartRepository.findAllByBuyerEmail(request.getBuyerEmail());
        if (!cartItemList.isEmpty()) {
            for(CartItem cartItem : cartItemList)
            {
                itemService.deleteByItem(cartItem.getItem() , cartItem.getQuantity());
            }
            cartRepository.deleteAll(cartItemList);
        }
        return "Checkout success";
    }

    private boolean validToken(String token) throws CredentialException {
        LoginToken loginToken = loginService.findTokenByTokenString(token);
        return loginToken.getExpireTime().isAfter(LocalDateTime.now());

    }

    public String addToCart(AddToCartRequest request) throws Exception {
        AppUser appUser = appUserService.getAppUserByEmail(request.getBuyerEmail());

        if(!loginService.tokenValid(request.getToken(),appUser))
        {
            throw new CredentialException("Invalid token");
        }


        Item item = itemService.findItemByName(request.getItemName());
        if(item.getQuantity() <1)
        {
            return "Out of stock";
        }

        String itemName = item.getItemName();

        List<CartItem> cartItems = cartRepository.findAllByBuyerEmail(request.getBuyerEmail());
        if(!cartItems.isEmpty())
        {
            for(CartItem cartItem : cartItems)
            {
                if(cartItem.getItem().getItemName().equals(request.getItemName()))
                {
                    //increase number and save
                    cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
                    cartRepository.save(cartItem);
                    return "Increased number of " + cartItem.getItem().getItemName()+ " for " + request.getBuyerEmail();
                }
            }

        }
        CartItem cartItem = new CartItem();
        cartItem.setItem(item);
        cartItem.setBuyerEmail(appUser.getEmail());
        cartItem.setQuantity(request.getQuantity());
        cartRepository.save(cartItem);
        return "Saved " + cartItem.getBuyerEmail() + " -- " + itemName;

    }

    public String deleteFromCart(DeleteFromCartRequest request) throws Exception {
        //delete one
        AppUser appUser = appUserService.getAppUserByEmail(request.getBuyerEmail());

        if(!loginService.tokenValid(request.getToken(),appUser))
        {
            throw new CredentialException("Invalid token");
        }


        Item item = itemService.findItemByName(request.getItemName());
        String itemName = item.getItemName();
        List<CartItem> cartItems = cartRepository.findAllByBuyerEmail(request.getBuyerEmail());
        if(!cartItems.isEmpty())
        {
            for(CartItem cartItem : cartItems)
            {
                if(cartItem.getItem().getItemName().equals(request.getItemName()))
                {
                    cartItem.setQuantity(cartItem.getQuantity() - request.getQuantity());
                    if(cartItem.getQuantity()<=0) {
                        cartRepository.delete(cartItem);
                        return "Deleted all " +
                                cartItem.getItem().getItemName() +
                                " for " + request.getBuyerEmail();
                    }
                    else {
                        cartRepository.save(cartItem);
                        return "Deleted " + request.getQuantity() + " " +
                                cartItem.getItem().getItemName() +
                                " for " + request.getBuyerEmail();
                    }
                }
            }

        }

       return "Item " + request.getItemName() + " not found in " + request.getBuyerEmail() + " cart";
    }
}
