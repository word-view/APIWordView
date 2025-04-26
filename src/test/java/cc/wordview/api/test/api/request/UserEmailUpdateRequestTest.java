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

import cc.wordview.api.exception.RequestValidationException;
import cc.wordview.api.request.user.UserEmailUpdateRequest;
import org.junit.jupiter.api.Test;

import static cc.wordview.api.request.ExceptionTemplate.emptyOrNull;
import static cc.wordview.api.test.api.request.TestException.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class UserEmailUpdateRequestTest {
    @Test
    void noException() throws Exception {
        assertDoesNotThrow(() -> {
            UserEmailUpdateRequest request = new UserEmailUpdateRequest();

            request.setOldEmail("arthur.araujo@gmail.com");
            request.setNewEmail("arthur.araujo@hotmail.com");
            request.setPassword("password");

            request.validate();
        });
    }

    @Test
    void emailsEqual() throws Exception {
        assertThrows(() -> {
            UserEmailUpdateRequest request = new UserEmailUpdateRequest();

            request.setOldEmail("arthur.araujo@hotmail.com");
            request.setNewEmail("arthur.araujo@hotmail.com");
            request.setPassword("password");

            request.validate();
        }, new RequestValidationException("Emails cannot be equal").getMessage());
    }

    @Test
    void oldEmailEmpty() throws Exception {
        assertThrows(() -> {
            UserEmailUpdateRequest request = new UserEmailUpdateRequest();

            request.setOldEmail("");
            request.setNewEmail("arthur.araujo@hotmail.com");
            request.setPassword("password");

            request.validate();
        }, emptyOrNull("oldEmail").getMessage());
    }

    @Test
    void oldEmailNull() throws Exception {
        assertThrows(() -> {
            UserEmailUpdateRequest request = new UserEmailUpdateRequest();

            request.setNewEmail("arthur.araujo@hotmail.com");
            request.setPassword("password");

            request.validate();
        }, emptyOrNull("oldEmail").getMessage());
    }

    @Test
    void newEmailEmpty() throws Exception {
        assertThrows(() -> {
            UserEmailUpdateRequest request = new UserEmailUpdateRequest();

            request.setOldEmail("arthur.araujo@gmail.com");
            request.setNewEmail("");
            request.setPassword("password");

            request.validate();
        }, emptyOrNull("newEmail").getMessage());
    }

    @Test
    void newEmailNull() throws Exception {
        assertThrows(() -> {
            UserEmailUpdateRequest request = new UserEmailUpdateRequest();

            request.setOldEmail("arthur.araujo@gmail.com");
            request.setPassword("password");

            request.validate();
        }, emptyOrNull("newEmail").getMessage());
    }

    @Test
    void passwordEmpty() throws Exception {
        assertThrows(() -> {
            UserEmailUpdateRequest request = new UserEmailUpdateRequest();

            request.setOldEmail("arthur.araujo@gmail.com");
            request.setNewEmail("arthur.araujo@hotmail.com");
            request.setPassword("");

            request.validate();
        }, emptyOrNull("password").getMessage());
    }

    @Test
    void passwordNull() throws Exception {
        assertThrows(() -> {
            UserEmailUpdateRequest request = new UserEmailUpdateRequest();

            request.setOldEmail("arthur.araujo@gmail.com");
            request.setNewEmail("arthur.araujo@hotmail.com");

            request.validate();
        }, emptyOrNull("password").getMessage());
    }


    @Test
    void fieldsEmpty() throws Exception {
        assertThrows(() -> {
            UserEmailUpdateRequest request = new UserEmailUpdateRequest();

            request.setOldEmail("");
            request.setNewEmail("");
            request.setPassword("");

            request.validate();
        }, emptyOrNull("oldEmail").getMessage());
    }

    @Test
    void fieldsNull() throws Exception {
        assertThrows(() -> {
            UserEmailUpdateRequest request = new UserEmailUpdateRequest();
            request.validate();
        }, emptyOrNull("oldEmail").getMessage());
    }
}
