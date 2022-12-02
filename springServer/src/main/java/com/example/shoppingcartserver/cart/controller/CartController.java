package com.example.shoppingcartserver.cart.controller;


import com.example.shoppingcartserver.cart.CartService;
import com.example.shoppingcartserver.cart.request.AddToCartRequest;
import com.example.shoppingcartserver.cart.request.DeleteFromCartRequest;
import com.example.shoppingcartserver.cart.request.GetCartRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;

/**
 * Controller layer for Cart module, handles REST API calls related to cart
 * @author aiden, vivek
 */
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping(path = "/cart")
public class CartController {

    private CartService cartService;

    /**
     * Post cart/state, for getting cart items for user
     * @param request token
     * @return Json array containing list of cartItems for that token owner
     * @throws CredentialException when user DNE or Token does not match
     */
    @PostMapping(path = "/state")
    public String getCart(@RequestBody GetCartRequest request) throws CredentialException {
        return cartService.getCart(request);
    }

    /**
     * Post /cart, for adding item to user's cart
     * @param request itemName, quantity, token
     * @return status string
     * @throws Exception when user or item DNE or Token does not match
     */
    @PostMapping
    public String addToCart(@RequestBody AddToCartRequest request) throws Exception
    {
        return cartService.addToCart(request);
    }

    /**
     * Del /cart, for delete item from cart
     * @param request token, itemName, quantity
     * @return status string
     * @throws Exception when user or item DNE or Token does not match
     */
    @DeleteMapping
    public String deleteFromCart(@RequestBody DeleteFromCartRequest request) throws Exception
    {
        return cartService.deleteFromCart(request);
    }

    /**
     * Put cart/, for checkout all item from cart, and decreasing item stock number
     * @param request token
     * @return status string
     * @throws Exception when user or item DNE, Token does not match, or out of stock
     */
    @PutMapping
    public String checkoutCart(@RequestBody GetCartRequest request) throws Exception
    {
        return cartService.checkout(request);
    }
}
