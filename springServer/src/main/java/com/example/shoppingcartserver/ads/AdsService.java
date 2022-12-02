package com.example.shoppingcartserver.ads;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class AdsService {

    private final AdsRepository adsRepository;
    private static final String DEFAULT_AD = "Ads slot for lease";

    /**
     * Randomly select one ad entry from database, if there are no ads, display
     * @return A string of ad text
     */
    public String getRandomAds() {
        List<Ads> adsList = adsRepository.findAll().stream().toList();
        int listLen = adsList.size();
        if(listLen < 1)
        {
            return DEFAULT_AD;
        }
        Random random = new Random();
        return adsList.get(random.nextInt(0,listLen-1)).getContent();

    }
}
