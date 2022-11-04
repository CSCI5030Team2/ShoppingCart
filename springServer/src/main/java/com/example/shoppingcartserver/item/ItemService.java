package com.example.shoppingcartserver.item;

import com.alibaba.fastjson.JSON;
import com.example.shoppingcartserver.item.request.AddItemRequest;
import com.example.shoppingcartserver.item.request.AdminAddItemRequest;
import com.example.shoppingcartserver.item.request.BuyItemRequest;
import com.example.shoppingcartserver.item.request.DeleteItemRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author aiden
 */
@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public String getItem()
    {
        List<Item> itemList = itemRepository.findAll();

        //translate iterable object to json object
        return JSON.toJSONString(itemList);
    }

    public String buyItem(BuyItemRequest request)
    {
        return "Works " + request.getItemName() + request.getQuantity();
    }


    public String addItem(AdminAddItemRequest request) {

        //todo verify the email and token


        //1 check if admin exist

        //2 check if that admin is logged in
            //1 not expired
            //user refernece correct



        Item item = new Item(
                request.getItemName(),
                request.getQuantity(),
                request.getPrice()
        );

        boolean itemExist = itemRepository.findByItemName(request.getItemName()).isPresent();

        if(itemExist) {
            itemRepository.updateItemByName(request.getItemName(),request.getPrice(),request.getQuantity());
            return request.getItemName() + " already exist, updated with new price and new quantity";
        }

        itemRepository.save(item);
        return "Saved " + item.getItemName();
    }

    public String deleteItem(DeleteItemRequest request) {
        Item item = new Item(
                request.getItemName(),
                0,
                (float) 0
        );
        boolean itemExist = itemRepository.findByItemName(request.getItemName()).isPresent();
        if(itemExist)
        {
            itemRepository.deleteItemByName(request.getItemName());
            return "Deleted: " + request.getItemName();
        }
        else
        {
            return "Item Name: " + request.getItemName() + " not found";
        }

    }


}
