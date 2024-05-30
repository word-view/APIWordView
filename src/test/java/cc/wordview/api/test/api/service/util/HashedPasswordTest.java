package cc.wordview.api.test.api.service.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static cc.wordview.api.test.api.request.TestException.*;

import org.junit.Test;

import cc.wordview.api.database.entity.User;
import cc.wordview.api.service.util.HashedPassword;

public class HashedPasswordTest {
        @Test
        public void hashPassword() throws Exception {
                User mockUser = new User();

                mockUser.setEmail("test@gmail.com");
                mockUser.setPassword("123456789");

                HashedPassword password = new HashedPassword(mockUser);

                assertEquals("ff13706a956c3ac97560f7ba6e1f0a0d", password.getValue());
        }

        @Test
        public void nullEmail() throws Exception {
                assertThrows(() -> {
                        User mockUser = new User();

                        mockUser.setPassword("123456789");

                        new HashedPassword(mockUser);
                }, "Neither email or password should be null");
        }

        @Test
        public void emptyEmail() throws Exception {
                assertThrows(() -> {
                        User mockUser = new User();

                        mockUser.setEmail("");
                        mockUser.setPassword("123456789");

                        new HashedPassword(mockUser);
                }, "Neither email or password should be empty");
        }

        @Test
        public void nullPassword() throws Exception {
                assertThrows(() -> {
                        User mockUser = new User();

                        mockUser.setEmail("test@gmail.com");

                        new HashedPassword(mockUser);
                }, "Neither email or password should be null");
        }

        @Test
        public void emptyPassword() throws Exception {
                assertThrows(() -> {
                        User mockUser = new User();

                        mockUser.setEmail("test@gmail.com");
                        mockUser.setPassword("");

                        new HashedPassword(mockUser);
                }, "Neither email or password should be empty");
        }
}
