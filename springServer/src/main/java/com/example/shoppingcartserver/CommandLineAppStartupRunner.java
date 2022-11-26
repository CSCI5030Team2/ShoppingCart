package com.example.shoppingcartserver;

import com.example.shoppingcartserver.ads.Ads;
import com.example.shoppingcartserver.ads.AdsRepository;
import com.example.shoppingcartserver.appuser.AppUser;
import com.example.shoppingcartserver.appuser.AppUserRepository;
import com.example.shoppingcartserver.appuser.AppUserRole;
import com.example.shoppingcartserver.item.Item;
import com.example.shoppingcartserver.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * create default db items after server reboot
 * @author aiden
 */
@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    AdsRepository adsRepository;

    @Override
    public void run(String... args)  {
        if(appUserRepository == null || itemRepository == null)
        {
            System.err.println("Repo init failed");
            return;
        }
        try {
            createAdmin();
            createTestUser();
            createItems();
            createAds();
        } catch (Exception e) {
            System.err.println("Failed inserting default database items");
            e.printStackTrace();
        }
    }

    private void createAds() {
        if(adsRepository.findAll().isEmpty()) {
            Ads ads = new Ads();
            ads.setContent("iPhone 14 pro is so great, buy now");
            adsRepository.save(ads);
            ads = new Ads();
            ads.setContent("Try our latest earphones: AirPods pro 2!");
            adsRepository.save(ads);
            ads = new Ads();
            ads.setContent("Samsung phones suck, buy iPhones instead");
            adsRepository.save(ads);
        }
    }

    private void createTestUser() {
        String fakeEmail = "user@shoppingcart.com";
        if(appUserRepository.findByEmail(fakeEmail).isPresent())
        {
            return;
        }
        AppUser user = new AppUser();
        user.setLocked(false);
        user.setEnable(true);
        user.setEmail(fakeEmail);
        user.setPassword(new BCryptPasswordEncoder().encode("a123456"));
        user.setFirstName("TEST");
        user.setLastName("USER");
        user.setAppUserRole(AppUserRole.USER);
        appUserRepository.save(user);
    }

    private void createAdmin()
    {
        String fakeEmail = "admin@shoppingcart.com";
        if(appUserRepository.findByEmail(fakeEmail).isPresent())
        {
            return;
        }
        AppUser admin = new AppUser();
        admin.setLocked(false);
        admin.setEnable(true);
        admin.setEmail(fakeEmail);
        admin.setPassword(new BCryptPasswordEncoder().encode("a123456"));
        admin.setFirstName("MASTER");
        admin.setLastName("ADMIN");
        admin.setAppUserRole(AppUserRole.ADMIN);
        appUserRepository.save(admin);
    }

    private void createItems()
    {
        String itemName = "iPhone 14 pro max red 1TB deluxe edition unlocked";
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(1299.99f);
        item.setQuantity(1);
        if(itemRepository.findByItemName(itemName).isPresent())
        {
            itemRepository.deleteItemByName(itemName);
        }
        itemRepository.save(item);
        item = new Item();
        itemName = "iPhone 14 pro";
        item.setItemName(itemName);
        item.setPrice(999f);
        item.setQuantity(99);
        if(itemRepository.findByItemName(itemName).isPresent())
        {
            itemRepository.deleteItemByName(itemName);
        }
        itemRepository.save(item);
        item = new Item();
        itemName = "iPhone 16 pro";
        item.setItemName(itemName);
        item.setPrice(999f);
        item.setQuantity(0);
        if(itemRepository.findByItemName(itemName).isPresent())
        {
            itemRepository.deleteItemByName(itemName);
        }
        itemRepository.save(item);
    }
}