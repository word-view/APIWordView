/*
 * Copyright (c) 2025 Arthur Araujo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

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
