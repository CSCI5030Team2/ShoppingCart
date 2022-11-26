package com.example.shoppingcartserver.ads;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class AdsService {

    private final AdsRepository adsRepository;

    public String getRandomAds() {
        List<Ads> adsList = adsRepository.findAll().stream().toList();
        int listLen = adsList.size();
        if(listLen < 1)
        {
            return "Ads slot for lease";
        }
        Random random = new Random();
        return adsList.get(random.nextInt(0,listLen-1)).getContent();

    }
}
