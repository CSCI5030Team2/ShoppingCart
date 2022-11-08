//package com.example.shoppingcartserver.appuser;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.assertj.core.api.AssertionsForClassTypes.fail;
//
///**
// * AppUser Repository layer Test
// * @author vivek
// */
//@DataJpaTest
////this starts the jpa instance
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
////this gives test class access to the database
//class AppUserRepositoryTest {
//    @Autowired
//    private AppUserRepository appUserRepository;
//    private AppUser appUser;
//
//    @BeforeEach
//    void setUp() {
//        this.appUser = new AppUser();
//    }
//    @Test
//    void saveAppUserWithUser()
//    {
//        setupAddUser();
//        Assertions.assertDoesNotThrow(() -> appUserRepository.save(this.appUser) );
//    }
//
//    @Test
//    void itShouldFindByEmail() {
//        //given
//        setupAddUser();
//        appUserRepository.save(appUser);
//        //when
//        Optional<AppUser> expected = appUserRepository.findByEmail(appUser.getEmail());
//        //then
//        assertThat(expected).isPresent();
//    }
//
//    @Test
//    void itShouldFindById() {
//        //given
//        setupAddUser();
//        appUserRepository.save(appUser);
//        //when
//        Optional<AppUser> expected = appUserRepository.findById(appUser.getId());
//        //then
//        assertThat(expected).isPresent();
//    }
//    @AfterEach
//    void tearDown()
//    {
//        try {
//            appUserRepository.delete(this.appUser);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        this.appUser = null;
//    }
//
//    void setupAddUser()
//    {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        //this.appUser.setId(100L);
//        this.appUser.setAppUserRole(AppUserRole.USER);
//        this.appUser.setFirstName("TEST_FIRST_NAME");
//        this.appUser.setLastName("TEST_LAST_NAME");
//        this.appUser.setEmail("test@test.com");
//        this.appUser.setPassword(bCryptPasswordEncoder.encode("PASSWORD"));
//    }
//
//
//}