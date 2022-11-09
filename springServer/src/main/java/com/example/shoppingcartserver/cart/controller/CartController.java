package com.example.shoppingcartserver.cart.controller;


import com.example.shoppingcartserver.cart.CartService;
import com.example.shoppingcartserver.cart.request.AddToCartRequest;
import com.example.shoppingcartserver.cart.request.DeleteFromCartRequest;
import com.example.shoppingcartserver.cart.request.GetCartRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;

/**
 * @author aiden
 */
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping(path = "/cart")
public class CartController {

    private CartService cartService;

    @GetMapping
    public String getCart(@RequestBody GetCartRequest request)
    {
        return cartService.getCart(request);
    }

    @PostMapping
    public String addToCart(@RequestBody AddToCartRequest request) throws Exception {
        return cartService.addToCart(request);
    }



    @DeleteMapping
    public String deleteFromCart(@RequestBody DeleteFromCartRequest request) throws Exception {
        return cartService.deleteFromCart(request);
    }


    @PutMapping
    public String checkoutCart(@RequestBody GetCartRequest request) throws Exception { return cartService.checkout(request);}


}
