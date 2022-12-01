package com.example.shoppingcartserver.cart.controller;


import com.example.shoppingcartserver.cart.CartService;
import com.example.shoppingcartserver.cart.request.AddToCartRequest;
import com.example.shoppingcartserver.cart.request.DeleteFromCartRequest;
import com.example.shoppingcartserver.cart.request.GetCartRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author aiden, vivek
 */
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping(path = "/cart")
public class CartController {

    private CartService cartService;

    /**
     * @param request login token
     * @return JSON String or Credential Exception
     */
    @PostMapping(path = "/state")
    public String getCart(@RequestBody GetCartRequest request) throws Exception {
        return cartService.getCart(request);
    }

    /**
     * @param request itemName, quantity, token
     * @return String of Exception
     */
    @PostMapping
    public String addToCart(@RequestBody AddToCartRequest request) throws Exception
    {
        return cartService.addToCart(request);
    }

    /**
     * @param request token, itemName, quantity
     * @return String of Exception
     */
    @DeleteMapping
    public String deleteFromCart(@RequestBody DeleteFromCartRequest request) throws Exception
    {
        return cartService.deleteFromCart(request);
    }

    /**
     * @param request login token
     * @return JSON String or Credential Exception
     */
    @PutMapping
    public String checkoutCart(@RequestBody GetCartRequest request) throws Exception
    {
        return cartService.checkout(request);
    }
}
