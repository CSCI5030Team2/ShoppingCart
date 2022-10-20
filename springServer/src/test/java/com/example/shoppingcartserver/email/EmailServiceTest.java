package com.example.shoppingcartserver.email;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class EmailServiceTest {

    @Test
    @Disabled
    void testSend() {
        EmailService emailService = new EmailService();

        emailService.send("zbh321412@gmail.com", "test", "test");

    }


}