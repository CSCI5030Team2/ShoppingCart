//package com.example.shoppingcartserver.password;
//
//import com.example.shoppingcartserver.appuser.AppUser;
//import com.example.shoppingcartserver.appuser.AppUserRepository;
//import com.example.shoppingcartserver.login.LoginTokenRepository;
//import com.example.shoppingcartserver.login.controller.LoginController;
//import com.example.shoppingcartserver.password.controller.ForgotPasswordController;
//import com.example.shoppingcartserver.password.request.ForgotPasswordRequest;
//import lombok.AllArgsConstructor;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * @author vivek
// */
//
//@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class ForgotPasswordServiceTest {
//
//    @Autowired
//    private ForgotPasswordController forgotPasswordController;
//
//    @Autowired
//    private AppUser appUser;
//    @Autowired
//    private AppUserRepository appUserRepository;
//
//    @Autowired
//    private LoginController loginController;
//
//    private final String firstName = "Vivek";
//    private final String lastName = "R";
//    private final String email = "vivekanandareddy.ponugoti@gmail.com";
//
//
//    @Order(1)
//    @Test
//    public void contextLoads () {
//        Assertions.assertNotNull(forgotPasswordController);
//    }
//
//    @Order(2)
//    @Test
//    public void testForgotPassword() {
//
//        ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest(
//            email,
//            firstName,
//            lastName,
//                ""
//        );
//        if(forgotPasswordRequest.getFirstName() == appUser.getFirstName()
//        && forgotPasswordRequest.getLastName() == appUser.getLastName())
//        {
//            assertDoesNotThrow(() -> forgotPasswordController.forgotPassword(forgotPasswordRequest));
//            Assertions.assertTrue(appUser.getEnable().equals(true));
//        }
//
//    }
//
//    @Test
//    public void testForgotReset() {
//
//    }
//
//    @Test
//    void testLogin() {
//    }
//}