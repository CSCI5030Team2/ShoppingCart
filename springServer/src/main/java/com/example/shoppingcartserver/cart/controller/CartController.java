package com.example.shoppingcartserver.cart.controller;


import com.example.shoppingcartserver.cart.CartService;
import com.example.shoppingcartserver.cart.request.AddToCartRequest;
import com.example.shoppingcartserver.cart.request.CheckoutCartRequest;
import com.example.shoppingcartserver.cart.request.DeleteFromCartRequest;
import com.example.shoppingcartserver.cart.request.GetCartRequest;
import lombok.AllArgsConstructor;
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

    private CartService cartService;

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

    @PostMapping
    public String checkOutCartRequest(@RequestBody CheckoutCartRequest request)
    {
        return cartService.checkout(request);
    }

    @PostRemove
    public String deleteFromCart(@RequestBody DeleteFromCartRequest request)
    {
        return cartService.deleteFromCart(request);
    }

}
