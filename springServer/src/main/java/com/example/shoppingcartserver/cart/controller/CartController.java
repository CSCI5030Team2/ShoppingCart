package com.example.shoppingcartserver.cart.controller;


import com.example.shoppingcartserver.cart.CartService;
import com.example.shoppingcartserver.cart.request.AddToCartRequest;
import com.example.shoppingcartserver.cart.request.GetCartRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    //todo  Delete from cart


    //todo put checkout
}
