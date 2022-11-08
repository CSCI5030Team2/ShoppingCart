package com.example.shoppingcartserver.item.controller;


import com.example.shoppingcartserver.item.ItemService;
import com.example.shoppingcartserver.item.request.AddItemRequest;
import com.example.shoppingcartserver.item.request.AdminAddItemRequest;
import com.example.shoppingcartserver.item.request.DeleteItemRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialExpiredException;

/**
 * @author aiden
 */
@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping(path = "/admin/item")
public class AdminItemController {
    private ItemService itemService;

    @PostMapping
    public String addItem(@RequestBody AdminAddItemRequest request)
    {
        return itemService.addItem(request);
    }

    @DeleteMapping
    public String deleteItem(@RequestBody DeleteItemRequest request) throws CredentialExpiredException {
        return itemService.deleteItem(request);
    }

    @PutMapping
    public String updateItem(@RequestBody AdminAddItemRequest request){
        return itemService.updateItem(request);
    }

}
