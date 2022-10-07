package com.example.shoppingcartserver.item.controller;

import com.example.shoppingcartserver.item.ItemService;
import com.example.shoppingcartserver.item.request.BuyItemRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author aiden
 */
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping(path = "/item")
public class ItemController {

    private ItemService itemService;
    /**
     * for looking up item
     * @return all items
     */
    @GetMapping
    public String getItems( )
    {
        return itemService.getItem();
    }


    /**
     * for making purchase
     * @param request request body
     * @return status
     */
    @PostMapping
    public String buyItem(@RequestBody BuyItemRequest request)
    {
        return itemService.buyItem(request);
    }
}

