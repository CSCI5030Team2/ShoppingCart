package com.example.shoppingcartserver.email;

/**
 * @author aiden
 */
public interface EmailSender {
    /**
     * All-purpose email sending api, will use "csci5030team2@outlook.com" as sender email address
     * @param target Email address of receiver
     * @param subject Email Subject title
     * @param content Email body
     */
    void send(String target,String subject, String content);
}
