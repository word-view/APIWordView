package cc.wordview.api.test.api.request;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cc.wordview.api.request.user.CreateRequest;

import static cc.wordview.api.test.api.request.TestException.*;
import static cc.wordview.api.request.ExceptionTemplate.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRequestTest {

        @Test
        public void noException() throws Exception {
                assertDoesNotThrow(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setUsername("arthur");
                        request.setPassword("senha23213");
                        request.setEmail("arthur@gmail.com");

                        request.toEntity();
                });
        }

        @Test
        public void usernameWithSpecialChars() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setUsername("asda@!#!@");
                        request.setPassword("senha23213");
                        request.setEmail("arthur@gmail.com");

                        request.toEntity();
                }, specialChars("'name'").getMessage());
        }

        @Test
        public void usernameEmpty() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setUsername("");
                        request.setPassword("senha23213");
                        request.setEmail("arthur@gmail.com");

                        request.toEntity();
                }, emptyOrNull("'name'").getMessage());
        }

        @Test
        public void usernameNull() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setPassword("senha23213");
                        request.setEmail("arthur@gmail.com");

                        request.toEntity();
                }, emptyOrNull("'name'").getMessage());
        }

        @Test
        public void emailInvalid() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setUsername("Arthur");
                        request.setPassword("senha23213");
                        request.setEmail("arthur@cmake.com");

                        request.toEntity();
                }, invalid("'email'").getMessage());
        }

        @Test
        public void emailEmpty() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setUsername("Arthur");
                        request.setPassword("senha23213");
                        request.setEmail("");

                        request.toEntity();
                }, emptyOrNull("'email'").getMessage());
        }

        @Test
        public void emailNull() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setUsername("Arthur");
                        request.setPassword("senha23213");

                        request.toEntity();
                }, emptyOrNull("'email'").getMessage());
        }
        
        @Test
        public void passwordEmpty() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setUsername("Arthur");
                        request.setPassword("");
                        request.setEmail("arthur@gmail.com");

                        request.toEntity();
                }, emptyOrNull("'password'").getMessage());
        }

        @Test
        public void passwordNull() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setUsername("Arthur");
                        request.setEmail("arthur@gmail.com");

                        request.toEntity();
                }, emptyOrNull("'password'").getMessage());
        }
}
