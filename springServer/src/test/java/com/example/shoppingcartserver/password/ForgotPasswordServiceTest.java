// package com.example.shoppingcartserver.password;

// import com.example.shoppingcartserver.appuser.AppUserRepository;
// import com.example.shoppingcartserver.login.controller.LoginController;
// import com.example.shoppingcartserver.login.request.CheckStateRequest;
// import com.example.shoppingcartserver.login.request.LoginRequest;
// import com.example.shoppingcartserver.password.controller.ForgotPasswordController;
// import com.example.shoppingcartserver.password.controller.ResetPasswordController;
// import com.example.shoppingcartserver.password.request.ForgotPasswordRequest;
// import com.example.shoppingcartserver.password.request.ForgotResetRequest;
// import com.example.shoppingcartserver.password.request.ResetPasswordRequest;
// import org.junit.jupiter.api.*;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
// import org.springframework.boot.test.context.SpringBootTest;

// import javax.security.auth.login.CredentialException;
// import javax.security.auth.login.CredentialExpiredException;

// import static org.junit.jupiter.api.Assertions.*;

// /**
//  * @author vivek
//  */

// @SpringBootTest
// @AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
// @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// class ForgotPasswordServiceTest {

//     @Autowired
//     private ForgotPasswordController forgotPasswordController;

//     @Autowired
//     private AppUserRepository appUserRepository;

//     @Autowired
//     private LoginController loginController;

//     private final String email = "admin@shoppingcart.com";
//     private final String firstName = "MASTER";
//     private final String lastName = "ADMIN";
//     private final String url = "http://localhost:3000/reset";

//     @Order(1)
//     @Test
//     void checkDB () {
//         Assertions.assertNotNull(loginController);
//         assertTrue(appUserRepository.findByEmail(email).isPresent());
//     }

//     @Order(2)
//     @Test
//     public void testForgotPassword() throws CredentialException {

//         ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest(
//                 email,
//                 firstName,
//                 lastName,
//                 url
//         );

//         assertDoesNotThrow(()-> forgotPasswordController.forgotPassword(forgotPasswordRequest));
//         assertTrue(appUserRepository.findByEmail(email).isPresent());
//     }

//     @Order(3)
//     @Test
//     public void testForgotReset() throws Exception {

//         ForgotResetRequest forgotResetRequest = new ForgotResetRequest(
//                 email,
//                 "a1234567"
//         );

//         assertDoesNotThrow(()-> forgotPasswordController.forgotReset(forgotResetRequest));
//         assertTrue(appUserRepository.findByEmail(email).isPresent());
//     }

//     @Order(4)
//     @Test
//     void testLogin() throws CredentialExpiredException {
//         LoginRequest loginRequest = new LoginRequest(
//                 email,
//                 "a1234567"
//         );
//         assertDoesNotThrow(() -> loginController.login(loginRequest));
//         CheckStateRequest checkStateRequest = new CheckStateRequest();
//         checkStateRequest.setEmail(email);
//         assertEquals("OK", loginController.checkState(checkStateRequest));

//     }
// }
