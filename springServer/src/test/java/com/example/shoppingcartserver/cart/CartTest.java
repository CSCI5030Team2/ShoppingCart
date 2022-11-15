package com.example.shoppingcartserver.cart;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.example.shoppingcartserver.cart.controller.CartController;
import com.example.shoppingcartserver.cart.request.AddToCartRequest;
import com.example.shoppingcartserver.cart.request.DeleteFromCartRequest;
import com.example.shoppingcartserver.cart.request.GetCartRequest;
import com.example.shoppingcartserver.login.controller.LoginController;
import com.example.shoppingcartserver.login.request.LoginRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class CartTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartController controller;

    @Autowired
    private LoginController loginController;

    @BeforeEach
    void setUp() {
    }
    @AfterEach
    void tearDown() {
    }


    @RepeatedTest(3)
    public void testAddCartItem() throws Exception {

        String email = "user@shoppingcart.com";
        String pw = "a123456";
        String itemName = "iPhone 14 pro";

        if(!cartRepository.findAllByBuyerEmail(email).isEmpty())
        {
            assertDoesNotThrow(()->cartRepository.deleteAllByBuyerEmail(email));
        }
        JSONArray array = JSON.parseArray(loginController.login(new LoginRequest(email,pw)));
        String token = (String) array.get(0);


        AddToCartRequest addToCartRequest = new AddToCartRequest(
                itemName,
                1,
                token

        );
        assertDoesNotThrow(()->controller.addToCart(addToCartRequest));
        assertFalse(cartRepository.findAllByBuyerEmail(email).isEmpty());
        assertDoesNotThrow(()->controller.deleteFromCart(new DeleteFromCartRequest(
                token,
                itemName,
                1

        )));
        assertTrue(cartRepository.findAllByBuyerEmail(email).isEmpty());

    }

    @Test
    public void testDeleteCartItem() throws Exception {

        String email = "user@shoppingcart.com";
        String pw = "a123456";
        String itemName = "iPhone 14 pro";

        if (!cartRepository.findAllByBuyerEmail(email).isEmpty()) {
            assertDoesNotThrow(() -> cartRepository.deleteAllByBuyerEmail(email));
        }

        JSONArray array = JSON.parseArray(loginController.login(new LoginRequest(email, pw)));
        String token = (String) array.get(0);

        DeleteFromCartRequest deleteFromCartRequest = new DeleteFromCartRequest(
                token,
                itemName,
                10

        );
        assertDoesNotThrow(() -> controller.deleteFromCart(deleteFromCartRequest));
        assertFalse(cartRepository.findAllByBuyerEmail(email).isEmpty());
        assertDoesNotThrow(() -> controller.deleteFromCart(new DeleteFromCartRequest(
                token,
                itemName,
                1

        )));
        assertTrue(cartRepository.findAllByBuyerEmail(email).isEmpty());
    }


    @Test
    public void testCheckout() throws Exception {
        String email = "user@shoppingcart.com";
        String pw = "a123456";

        if (!cartRepository.findAllByBuyerEmail(email).isEmpty()) {
            assertDoesNotThrow(() -> cartRepository.deleteAllByBuyerEmail(email));
        }

        JSONArray array = JSON.parseArray(loginController.login(new LoginRequest(email, pw)));
        String token = (String) array.get(0);

        GetCartRequest getCartRequest = new GetCartRequest(
                token
        );
        assertDoesNotThrow(() -> controller.getCart(getCartRequest));
        assertFalse(cartRepository.findAllByBuyerEmail(email).isEmpty());
        assertDoesNotThrow(() -> controller.checkoutCart(new GetCartRequest(
                        token
        )));
        assertTrue(cartRepository.findAllByBuyerEmail(email).isEmpty());
    }

}