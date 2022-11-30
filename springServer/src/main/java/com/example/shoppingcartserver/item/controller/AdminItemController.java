package com.example.shoppingcartserver.item.controller;


import com.example.shoppingcartserver.item.ItemService;
import com.example.shoppingcartserver.item.request.AddItemRequest;
import com.example.shoppingcartserver.item.request.AdminAddItemRequest;
import com.example.shoppingcartserver.item.request.DeleteItemRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;
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
    public String addItem(@RequestBody AdminAddItemRequest request) throws CredentialException {
        return itemService.addItem(request);
    }

    @PutMapping
    public String updateItem(@RequestBody AdminAddItemRequest request) throws CredentialException {
        return itemService.updateItem(request);
    }

    @DeleteMapping
<<<<<<< HEAD
    public String deleteItem(@RequestBody DeleteItemRequest request) throws CredentialExpiredException {
=======
    public String deleteItem(@RequestBody DeleteItemRequest request) throws CredentialException {
        System.out.println("DeleteItem Got: " + request.toString() );
>>>>>>> origin/bohan
        return itemService.deleteItem(request);
    }

}
