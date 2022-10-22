package com.example.shoppingcartserver.appuser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void itShouldFindByEmail() {
        //given
        String email = "vponugoti1@slu.edu";
        AppUser appUser = new AppUser(
                "vivek",
                "Reddy",
                email
        );
        appUserRepository.save(appUser);

        //when
        Optional<AppUser> expected = appUserRepository.findByEmail(email);
        //then
        assertThat(expected).isPresent();
    }


}