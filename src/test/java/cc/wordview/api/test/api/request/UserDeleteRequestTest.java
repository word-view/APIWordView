package cc.wordview.api.test.api.request;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cc.wordview.api.request.user.DeleteRequest;
import lombok.Getter;
import lombok.Setter;

import static cc.wordview.api.test.api.request.TestException.*;
import static cc.wordview.api.request.ExceptionTemplate.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Getter
@Setter
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDeleteRequestTest {
        public String token;
        public String password;
        public String email;

        @Test
        public void noException() throws Exception {
                assertDoesNotThrow(() -> {
                        DeleteRequest request = new DeleteRequest();

                        request.setToken("4e9394b4d2876b8741b10a");
                        request.setEmail("arthur@gmail.com");
                        request.setPassword("senha");

                        request.toEntity();
                });
        }

        @Test
        public void tokenEmpty() throws Exception {
                assertThrows(() -> {
                        DeleteRequest request = new DeleteRequest();

                        request.setToken("");
                        request.setEmail("arthur@gmail.com");
                        request.setPassword("senha");

                        request.toEntity();
                }, emptyOrNull("'token'").getMessage());
        }

        @Test
        public void tokenNull() throws Exception {
                assertThrows(() -> {
                        DeleteRequest request = new DeleteRequest();

                        request.setEmail("arthur@gmail.com");
                        request.setPassword("senha");

                        request.toEntity();
                }, emptyOrNull("'token'").getMessage());
        }


        @Test
        public void emailEmpty() throws Exception {
                assertThrows(() -> {
                        DeleteRequest request = new DeleteRequest();

                        request.setToken("4e9394b4d2876b8741b10a");
                        request.setEmail("");
                        request.setPassword("senha");

                        request.toEntity();
                }, emptyOrNull("'email'").getMessage());
        }


        @Test
        public void emailNull() throws Exception {
                assertThrows(() -> {
                        DeleteRequest request = new DeleteRequest();

                        request.setToken("4e9394b4d2876b8741b10a");
                        request.setPassword("senha");

                        request.toEntity();
                }, emptyOrNull("'email'").getMessage());
        }

        @Test
        public void passwordEmpty() throws Exception {
                assertThrows(() -> {
                        DeleteRequest request = new DeleteRequest();

                        request.setToken("4e9394b4d2876b8741b10a");
                        request.setEmail("arthur@gmail.com");
                        request.setPassword("");

                        request.toEntity();
                }, emptyOrNull("'password'").getMessage());
        }

        @Test
        public void passswordNull() throws Exception {
                assertThrows(() -> {
                        DeleteRequest request = new DeleteRequest();

                        request.setToken("4e9394b4d2876b8741b10a");
                        request.setEmail("arthur@gmail.com");

                        request.toEntity();
                }, emptyOrNull("'password'").getMessage());
        }
}
