package com.example.shoppingcartserver.item;

import com.alibaba.fastjson.JSON;
import com.example.shoppingcartserver.appuser.AppUser;
import com.example.shoppingcartserver.appuser.AppUserRole;
import com.example.shoppingcartserver.appuser.AppUserServiceImpl;
import com.example.shoppingcartserver.item.request.AdminAddItemRequest;
import com.example.shoppingcartserver.item.request.BuyItemRequest;
import com.example.shoppingcartserver.item.request.DeleteItemRequest;
import com.example.shoppingcartserver.login.LoginService;
import com.example.shoppingcartserver.login.token.LoginToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
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
        Optional<Item> optionalItem = itemRepository.findByItemName(request.getItemName());
        if(optionalItem.isPresent()) {
            Item item = optionalItem.get();

            if(item.getQuantity() >= request.getQuantity())
            {
                item.setQuantity(item.getQuantity() - request.getQuantity());
                itemRepository.save(item);
                return "You bought: " + request.getQuantity() +" of \""+ request.getItemName()+ "\" ";
            }
            else
            {
                if(item.getQuantity() > 0) {
                    return "Only " + item.getQuantity() + " left";
                }
                else
                {
                    return "Out of stock";
                }
            }
        }
        return "item do not exist";
    }


    public String addItem(AdminAddItemRequest request) throws CredentialException {
        LoginToken loginToken = loginService.findTokenByTokenString(request.getToken());
        AppUser appUser = appUserService.getAppUserByEmail(loginToken.getAppUser().getEmail());

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

    public String updateItem(AdminAddItemRequest request) throws CredentialException {
        LoginToken loginToken = loginService.findTokenByTokenString(request.getToken());
        AppUser appUser = loginToken.getAppUser();
        if(appUserService.userDoNotExist(appUser.getEmail()))
        {
            return "User do no exist";
        }
        if(!appUserService.isAdmin(appUser.getEmail()))
        {
            return "Only admin can add new item";
        }
        if(loginService.tokenValid(request.getToken(), appUser))
        {
            boolean itemExist = itemRepository.findByItemName(request.getItemName()).isPresent();

            if(itemExist) {
                itemRepository.updateItemByName(request.getItemName(),request.getPrice(),request.getQuantity());
                return request.getItemName() + " already exist, updated " + request.getItemName();
            }
            return "Item do not exist";
        }
        return "Failed, Invalid token";
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

    public String deleteItem(DeleteItemRequest request) throws CredentialException {
        LoginToken loginToken = loginService.findTokenByTokenString(request.getToken());
        AppUser appUser = loginToken.getAppUser();

        if(appUserService.userDoNotExist(appUser.getEmail()))
        {
            return "User do no exist";
        }
        if(!appUserService.isAdmin(appUser.getEmail()))
        {
            return "Only admin can add new item";
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


    /**
     * Only for cart checkout
     * @param item item instance
     * @param quantity integer, number of item to delete
     */
    public void deleteByItem(Item item, Integer quantity) throws Exception {

        Optional<Item> optionalItem = itemRepository.findByItemName(item.getItemName());
        if(optionalItem.isPresent()) {
            Item dbItem = optionalItem.get();

            if(dbItem.getQuantity() < quantity)
            {
                //don't checkout
                throw new Exception("out of stock");
            }

            itemRepository.updateItemByName(item.getItemName(),
                    item.getPrice(),
                    item.getQuantity() - quantity
            );
        }
    }
}
