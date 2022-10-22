package com.example.shoppingcartserver.appuser;

import com.example.shoppingcartserver.registration.RegistrationRequest;
import com.example.shoppingcartserver.registration.RegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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

    }

    @Test
    void loadUserByUsername() {
        appUser = new AppUser();
        appUser.setId(12L);
        appUser.setEmail("viv@slu.edu");

        Mockito.when(appUserRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(appUser));
        UserDetails user = appUserServiceImpl.loadUserByUsername("viv@slu.edu");
        assertTrue(true);
    }

    @Test
    void userNameNotFound() {
        Mockito.when(appUserRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, ()-> appUserServiceImpl.loadUserByUsername("vponugoti2@slu.edu") ) ;
    }

    @Test
    void signupUser() {

        // given

        //when

        //then

//        Mockito.when(appUserRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(appUser));
//        UserDetails user = appUserServiceImpl.loadUserByUsername("vponugoti1@slu.edu");
//        Mockito.when(appUserRepository.existsById(12L)).thenReturn(Boolean.TRUE);

    }

    @Test
    void signUpUserExist() {



    }
}