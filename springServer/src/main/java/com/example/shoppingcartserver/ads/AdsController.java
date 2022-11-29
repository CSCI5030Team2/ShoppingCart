package com.example.shoppingcartserver.ads;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping(path = "/ads")
public class AdsController {
    private AdsService service;

    @GetMapping
    public String getRandomAds() {
        return service.getRandomAds();

    }
}
