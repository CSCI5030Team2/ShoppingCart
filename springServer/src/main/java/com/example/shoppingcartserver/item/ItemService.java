package com.example.shoppingcartserver.item;

import com.alibaba.fastjson.JSON;
import com.example.shoppingcartserver.appuser.AppUser;
import com.example.shoppingcartserver.appuser.AppUserRole;
import com.example.shoppingcartserver.appuser.AppUserServiceImpl;
import com.example.shoppingcartserver.item.request.AdminAddItemRequest;
import com.example.shoppingcartserver.item.request.BuyItemRequest;
import com.example.shoppingcartserver.item.request.DeleteItemRequest;
import com.example.shoppingcartserver.login.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialExpiredException;
import java.util.List;
import java.util.Optional;

/**
 * @author aiden
 */
@Service
@AllArgsConstructor
public class ItemService {

    private final AppUserServiceImpl appUserService;
    private final ItemRepository itemRepository;
    private LoginService loginService;

    public String getItem()
    {
        List<Item> itemList = itemRepository.findAll();

        //translate iterable object to json object
        return JSON.toJSONString(itemList);
    }

    public String buyItem(BuyItemRequest request)
    {
        return "You bought: " + request.getQuantity() +" of \""+ request.getItemName()+ "\" ";
    }


    public String addItem(AdminAddItemRequest request) {

        AppUser appUser = appUserService.getAppUserByEmail(request.getEmail());

        if(appUser.getAppUserRole()!=AppUserRole.ADMIN)
        {
            return "Only admin can add new item";
        }

        if(loginService.tokenValid(request.getToken(), appUser))
        {
            boolean itemExist = itemRepository.findByItemName(request.getItemName()).isPresent();

            if(itemExist) {
                //itemRepository.updateItemByName(request.getItemName(),request.getPrice(),request.getQuantity());
                return request.getItemName() + " already exist, did nothing";
            }


            Item newItem = new Item();
            newItem.setItemName(request.getItemName());
            newItem.setQuantity(request.getQuantity());
            newItem.setPrice(request.getPrice());
            itemRepository.save(newItem);
            return "Saved " + newItem.getItemName();
        }
        return "Failed, Invalid token";
    }

    public String updateItem(AdminAddItemRequest request)
    {
        if(appUserService.userDoNotExist(request.getEmail()))
        {
            return "User do no exist";
        }
        if(!appUserService.isAdmin(request.getEmail()))
        {
            return "Only admin can add new item";
        }

        AppUser appUser = appUserService.getAppUserByEmail(request.getEmail());

        if(loginService.tokenValid(request.getToken(), appUser))
        {
            boolean itemExist = itemRepository.findByItemName(request.getItemName()).isPresent();

            if(itemExist) {
                itemRepository.updateItemByName(request.getItemName(),request.getPrice(),request.getQuantity());
                return request.getItemName() + " already exist, did nothing";
            }
            return "Item do not exist";
        }
        return "Failed, Invalid token";
    }


    public String deleteItem(DeleteItemRequest request) throws CredentialExpiredException {
        AppUser appUser = loginService.findAppUserByToken(request.getToken());


        if(appUser.getAppUserRole()!=AppUserRole.ADMIN)
        {
            return "No permission";
        }

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


    public Item findItemByName(String itemName) throws Exception {
        Optional<Item> optionalItem = itemRepository.findByItemName(itemName);
        if(optionalItem.isPresent())
        {
            return optionalItem.get();
        }
        else
        {
            throw new Exception("item " + itemName + " not found");
        }
    }


    /**
     * for cart checkout
     * @param item
     * @param quantity
     */
    public void deleteByItem(Item item, Integer quantity) {

        Optional<Item> optionalItem = itemRepository.findByItemName(item.getItemName());
        if(optionalItem.isPresent()) {
            Item dbItem = optionalItem.get();

            if(dbItem.getQuantity() < quantity)
            {
                //dont checkout
                return;
            }

            itemRepository.updateItemByName(item.getItemName(),
                    item.getPrice(),
                    item.getQuantity() - quantity
            );
        }
    }
}
