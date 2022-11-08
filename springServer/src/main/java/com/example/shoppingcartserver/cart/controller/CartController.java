package com.example.shoppingcartserver.cart.controller;


import com.example.shoppingcartserver.appuser.AppUserServiceImpl;
import com.example.shoppingcartserver.cart.CartService;
import com.example.shoppingcartserver.cart.request.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostRemove;

/**
 * @author aiden
 */
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping(path = "/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private AppUserServiceImpl appUserService;

    @GetMapping
    public String getCart(@RequestBody GetCartRequest request)
    {
        return cartService.getCart(request);
    }

    @PostMapping
    public String addToCart(@RequestBody AddToCartRequest request)
    {
        return cartService.addToCart(request);
    }

    @PutMapping
    public String checkOutCart(@RequestBody CheckoutCartRequest request)
    {
        return cartService.checkout(request);
    }

    @DeleteMapping
    public String deleteFromCart(@RequestBody DeleteFromCartRequest request)
    {
        return cartService.deleteFromCart(request);
    }

}
