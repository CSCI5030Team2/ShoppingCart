package com.example.shoppingcartserver.item;

import com.alibaba.fastjson.JSON;
import com.example.shoppingcartserver.appuser.AppUser;
import com.example.shoppingcartserver.appuser.AppUserRepository;
import com.example.shoppingcartserver.appuser.AppUserRole;
import com.example.shoppingcartserver.item.request.AdminAddItemRequest;
import com.example.shoppingcartserver.item.request.BuyItemRequest;
import com.example.shoppingcartserver.item.request.DeleteItemRequest;
import com.example.shoppingcartserver.login.LoginTokenRepository;
import com.example.shoppingcartserver.login.token.LoginToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author aiden
 */
@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final AppUserRepository appUserRepository;
    private final LoginTokenRepository loginTokenRepository;

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


    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public String addItem(AdminAddItemRequest request) {

        if(userDoNotExist(request.getEmail()))
        {
            return "User do no exist";
        }
        if(!isAdmin(request.getEmail()))
        {
            return "Only admin can add new item";
        }

        AppUser appUser = appUserRepository.findByEmail(request.getEmail()).get();

        if(tokenValid(request.getToken(), appUser))
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

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public String updateItem(AdminAddItemRequest request)
    {
        if(userDoNotExist(request.getEmail()))
        {
            return "User do no exist";
        }
        if(!isAdmin(request.getEmail()))
        {
            return "Only admin can add new item";
        }

        AppUser appUser = appUserRepository.findByEmail(request.getEmail()).get();

        if(tokenValid(request.getToken(), appUser))
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


    public String deleteItem(DeleteItemRequest request) {

        if(!tokenBelongToAdmin(request.getToken()))
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


    private boolean userDoNotExist(String email)
    {
        return appUserRepository.findByEmail(email).isEmpty();
    }

    private boolean tokenValid(String token, AppUser appUser)
    {
        Optional<LoginToken> optionalLoginToken = loginTokenRepository.findByToken(token);
        if(optionalLoginToken.isEmpty())
        {
            //did not log in
            return false;
        }
        LoginToken loginToken = optionalLoginToken.get();
        if(loginToken.getAppUser() != appUser)
        {
            //something wrong, prob hacker
            return false;
        }
        return loginToken.getExpireTime().isAfter(LocalDateTime.now());
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private boolean isAdmin(String email) {
        return appUserRepository.findByEmail(email).get().getAppUserRole() == AppUserRole.ADMIN;
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private boolean tokenBelongToAdmin(String token)
    {
        return loginTokenRepository.findByToken(token).get().getAppUser().getAppUserRole()==AppUserRole.ADMIN;
    }


}
