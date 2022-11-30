package com.example.shoppingcartserver.email;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Email service implementation
 * @author aiden
 */
@AllArgsConstructor
@Service
public class EmailService implements EmailSender {

    @Async
    @Override
    public void send(String to, String subject, String content) {
        // user credential for send email service, unencrypted
        String from = "csci5030team2@outlook.com";
        String password = "a123456+1";


        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.outlook.com");
        mailSender.setPort(587);
        mailSender.setUsername(from);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.outlook.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "true");

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            messageHelper.setText(content, true);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setFrom(from);
            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new IllegalStateException("Failed to send email");
        }
    }
}


