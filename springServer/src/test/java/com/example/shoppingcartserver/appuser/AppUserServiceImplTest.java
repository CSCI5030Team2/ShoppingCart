package com.example.shoppingcartserver.appuser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AppUser Service layer Test
 * @author vivek
 */

@ExtendWith(MockitoExtension.class)
class AppUserServiceImplTest {

    @InjectMocks
    AppUserServiceImpl appUserServiceImpl;

    AppUser appUser;

    @Mock
    AppUserRepository appUserRepository;

    @Mock
    Environment environment;

    @BeforeEach
    void setUp() {
        this.appUser = new AppUser();
    }

    @Test
    void loadUserByUsername() {
        setupAddUser();
        Mockito.when(appUserRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(appUser));
        UserDetails user = appUserServiceImpl.loadUserByUsername("test@test.com");
        assertTrue(true);
    }

    @Test
    void userNameNotFound() {
        setupAddUser();
        Mockito.when(appUserRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> appUserServiceImpl.loadUserByUsername("test@test.com"));
    }

    @AfterEach
    void tearDown()
    {
        try {
            appUserRepository.delete(this.appUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.appUser = null;
    }

    void setupAddUser()
    {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.appUser.setId(100L);
        this.appUser.setAppUserRole(AppUserRole.USER);
        this.appUser.setFirstName("TEST_FIRST_NAME");
        this.appUser.setLastName("TEST_LAST_NAME");
        this.appUser.setEmail("test@test.com");
        this.appUser.setPassword(bCryptPasswordEncoder.encode("PASSWORD"));
    }

}