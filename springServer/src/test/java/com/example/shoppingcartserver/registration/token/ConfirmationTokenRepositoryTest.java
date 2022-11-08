package com.example.shoppingcartserver.registration.token;

import com.example.shoppingcartserver.appuser.AppUser;
import com.example.shoppingcartserver.appuser.AppUserRepository;
import com.example.shoppingcartserver.appuser.AppUserRole;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;


/**
 * Confirmation Token Repo. layer Test
 * @author aiden
 */
@SpringBootTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class ConfirmationTokenRepositoryTest {
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    private ConfirmationToken confirmationToken;
    private AppUser appUser;

    @BeforeEach
    void setUp()
    {
        this.appUser = new AppUser();
        this.confirmationToken = new ConfirmationToken();
    }

    @Test
    void saveEmptyConfirmationToken()
    {
        Assertions.assertThrows(DataIntegrityViolationException.class,() -> confirmationTokenRepository.save(this.confirmationToken));
    }

    @Test
    void saveConfirmationTokenWithoutAppUser()
    {
        setupConfirmationTokenHelper();
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,() -> confirmationTokenRepository.save(this.confirmationToken));
    }

    @Test
    @Disabled("will fix later")
    void saveConfirmationTokenWithUser()
    {
        setupUserHelper();
        setupConfirmationTokenHelper();
        Assertions.assertDoesNotThrow(() -> appUserRepository.save(this.appUser) );
        Assertions.assertDoesNotThrow(() -> confirmationTokenRepository.save(this.confirmationToken) );
    }


    @AfterEach
    void tearDown()
    {
        try {
            appUserRepository.delete(this.appUser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            confirmationTokenRepository.delete(this.confirmationToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.appUser = null;
        this.confirmationToken = null;
    }


    void setupUserHelper()
    {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //this.appUser.setId(10000L);
        this.appUser.setAppUserRole(AppUserRole.USER);
        this.appUser.setFirstName("TEST_FIRST_NAME");
        this.appUser.setLastName("TEST_LAST_NAME");
        this.appUser.setEmail("testestest@test.com");
        this.appUser.setEnable(true);
        this.appUser.setLocked(false);
        this.appUser.setPassword(bCryptPasswordEncoder.encode("CLEARTEXT_PASSWORD"));
    }

    void setupConfirmationTokenHelper()
    {
        this.confirmationToken.setToken("TEST_TOKEN");
        this.confirmationToken.setCreateTime(LocalDateTime.now().minusMinutes(10));
        this.confirmationToken.setExpireTime(LocalDateTime.now().plusMinutes(30));
        this.confirmationToken.setConfirmTime(LocalDateTime.now());
        this.confirmationToken.setAppUser(appUser);
    }


}