package com.example.shoppingcartserver.item;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author aiden
 */

@RestController
@AllArgsConstructor
@RequestMapping(path = "/user/item")
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

    //TODO: security not implemented
    @PostMapping(path = "/add")
    public String addItem(@RequestBody AddItemRequest request)
    {
        return itemService.addItem(request);
    }



}
