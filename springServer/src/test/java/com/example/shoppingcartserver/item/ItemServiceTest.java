package com.example.shoppingcartserver.item;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.example.shoppingcartserver.CommandLineAppStartupRunner;
import com.example.shoppingcartserver.item.controller.AdminItemController;
import com.example.shoppingcartserver.item.controller.ItemController;
import com.example.shoppingcartserver.item.request.AdminAddItemRequest;
import com.example.shoppingcartserver.item.request.BuyItemRequest;
import com.example.shoppingcartserver.login.controller.AdminLoginController;
import com.example.shoppingcartserver.login.controller.LoginController;
import com.example.shoppingcartserver.login.request.LoginRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import javax.security.auth.login.CredentialException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemServiceTest {

    @Autowired
    private ItemController itemController;

    @Autowired
    private AdminLoginController adminLoginController;

    @Autowired
    private AdminItemController adminItemController;

    @Autowired
    private ItemRepository itemRepository;

    private final String email = "admin@shoppingcart.com";
    private final String newItemName = "Test Item";

    @Test
    @Order(1)
    public void setup() {

    }

    @Test
    @Order(2)
    void addItem() throws CredentialException {
        JSONArray array = JSON.parseArray( adminLoginController.login(new LoginRequest(email,"a123456")));
        String token = (String) array.get(0);
        assertDoesNotThrow(() -> adminItemController.addItem(new AdminAddItemRequest(
                newItemName,
                10,
                1.1f,
                token
        )));

        assertTrue(itemRepository.findByItemName(newItemName).isPresent());
    }

    @Test
    @Order(3)
    void updateItem() throws CredentialException {
        JSONArray array = JSON.parseArray( adminLoginController.login(new LoginRequest(email,"a123456")));
        String token = (String) array.get(0);
        assertDoesNotThrow(() -> adminItemController.updateItem(new AdminAddItemRequest(
                newItemName,
                100,
                1000.00f,
                token
        )));


        assertEquals(100, (int) itemRepository.findByItemName(newItemName).get().getQuantity());

    }
    @Test
    @Order(4)
    void buyItem() {
        assertDoesNotThrow(()->itemController.buyItem(new BuyItemRequest(email,newItemName,1)));
    }
    @Test
    @Order(5)
    void cleanup()
    {
        assertDoesNotThrow(() -> itemRepository.deleteItemByName(newItemName));
        assertTrue(itemRepository.findByItemName(newItemName).isEmpty());
    }
}