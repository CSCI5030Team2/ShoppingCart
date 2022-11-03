package com.example.shoppingcartserver.item.controller;


import com.example.shoppingcartserver.item.ItemService;
import com.example.shoppingcartserver.item.request.AddItemRequest;
import com.example.shoppingcartserver.item.request.AdminAddItemRequest;
import com.example.shoppingcartserver.item.request.DeleteItemRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author aiden
 */
@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping(path = "/admin/item")
public class AdminItemController {
    private ItemService itemService;

    //TODO: security not implemented
    @PostMapping
    public String addItem(@RequestBody AdminAddItemRequest request)
    {
        return itemService.addItem(request);
    }

    @DeleteMapping
    public String deleteItem(@RequestBody DeleteItemRequest request)
    {
        return itemService.deleteItem(request);
    }


}
