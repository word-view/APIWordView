package cc.wordview.api.test.api.request;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import static cc.wordview.api.test.api.request.TestException.*;

import org.junit.jupiter.api.Test;

import cc.wordview.api.request.user.UserUpdateRequest;

public class UserUpdateRequestTest {
        @Test
        void updateUsername() throws Exception {
                assertDoesNotThrow(() -> {
                        UserUpdateRequest request = new UserUpdateRequest();

                        request.setUsername("arthur");

                        request.toEntity();
                });
        }

        @Test
        void fieldsEmpty() throws Exception {
                assertThrows(() -> {
                        UserUpdateRequest request = new UserUpdateRequest();

                        request.setUsername("");

                        request.toEntity();
                }, "Specify at least 1 field");
        }

        @Test
        void fieldsNull() throws Exception {
                assertThrows(() -> {
                        UserUpdateRequest request = new UserUpdateRequest();
                        request.toEntity();
                }, "Specify at least 1 field");
        }
}
