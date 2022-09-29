package com.example.shoppingcartserver.item;

import com.alibaba.fastjson.JSON;
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


    public String addItem(AddItemRequest request) {
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
        return "Saved " + item;
    }

}
