package com.example.shoppingcartserver.email;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailServiceTest {

    @Test
    void testSend() {
        EmailService emailService = new EmailService();

        emailService.send("zbh321412@gmail.com", "test", "test");

    }
}