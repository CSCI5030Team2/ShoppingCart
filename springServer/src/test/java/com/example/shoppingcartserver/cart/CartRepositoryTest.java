package com.example.shoppingcartserver.cart;

import com.example.shoppingcartserver.appuser.AppUser;
import com.example.shoppingcartserver.item.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testAddCartItem() {



        CartItem cartItem = new CartItem();
        cartItem.setBuyerEmail("vponugoit1@slu.edu");
        cartItem.getItemId();
        cartItem.setQuantity(2);

        CartItem savedCartItem = cartRepository.save(cartItem);

        assertTrue(savedCartItem.getItemId() > 0 );
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
}