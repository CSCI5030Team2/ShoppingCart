package com.example.shoppingcartserver.email;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class EmailServiceTest {

    @Test
    @Disabled("This sends email to me, don't enable")
    void testSend() {
        EmailService emailService = new EmailService();

        emailService.send("zbh321412@gmail.com", "test", "test");

    }


}