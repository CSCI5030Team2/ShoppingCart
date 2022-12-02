package com.example.shoppingcartserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot server backend driver class, this starts tomcat server on port 8080 and instantiates all spring beans
 * @author aiden
 */
@SpringBootApplication
public class ShoppingCartServer {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartServer.class, args);
    }
}