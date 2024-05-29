package cc.wordview.api.test.api.request;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static cc.wordview.api.test.api.request.TestException.*;
import static cc.wordview.api.request.ExceptionTemplate.*;

import org.junit.jupiter.api.Test;

import cc.wordview.api.request.user.UserLoginRequest;

public class UserLoginRequestTest {
        @Test
        void noException() throws Exception {
                assertDoesNotThrow(() -> {
                        UserLoginRequest request = new UserLoginRequest();

                        request.setEmail("arthur@gmail.com");
                        request.setPassword("senha23213");

                        request.toEntity();
                });
        }

        @Test
        void emailEmpty() throws Exception {
                assertThrows(() -> {
                        UserLoginRequest request = new UserLoginRequest();

                        request.setEmail("");
                        request.setPassword("senha23213");

                        request.toEntity();
                }, emptyOrNull("email").getMessage());
        }

        @Test
        void emailNull() throws Exception {
                assertThrows(() -> {
                        UserLoginRequest request = new UserLoginRequest();

                        request.setPassword("senha23213");

                        request.toEntity();
                }, emptyOrNull("email").getMessage());
        }

        @Test
        void emailInvalid() throws Exception {
                assertThrows(() -> {
                        UserLoginRequest request = new UserLoginRequest();

                        request.setEmail("invalidemail.com");
                        request.setPassword("senha23213");

                        request.toEntity();
                }, invalid("email").getMessage());
        }

        @Test
        void passwordEmpty() throws Exception {
                assertThrows(() -> {
                        UserLoginRequest request = new UserLoginRequest();

                        request.setEmail("arthur@gmail.com");
                        request.setPassword("");

                        request.toEntity();
                }, emptyOrNull("password").getMessage());
        }

        @Test
        void passwordNull() throws Exception {
                assertThrows(() -> {
                        UserLoginRequest request = new UserLoginRequest();

                        request.setEmail("arthur@gmail.com");

                        request.toEntity();
                }, emptyOrNull("password").getMessage());
        }
}
