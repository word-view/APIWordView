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

public class UserEmailUpdateRequestTest extends RequestTest {
    @Test
    void noException() {
        UserEmailUpdateRequest request = new UserEmailUpdateRequest();

        request.setOldEmail("arthur.araujo@gmail.com");
        request.setNewEmail("arthur.araujo@hotmail.com");
        request.setPassword("password");

        assertValidationDoesNotThrow(request);
    }

    @Test
    void emailsEqual() throws Exception {
        UserEmailUpdateRequest request = new UserEmailUpdateRequest();

        request.setOldEmail("arthur.araujo@hotmail.com");
        request.setNewEmail("arthur.araujo@hotmail.com");
        request.setPassword("password");

        assertValidationThrows(request, new RequestValidationException("Emails cannot be equal"));
    }

    @Test
    void oldEmailEmpty() throws Exception {
        UserEmailUpdateRequest request = new UserEmailUpdateRequest();

        request.setOldEmail("");
        request.setNewEmail("arthur.araujo@hotmail.com");
        request.setPassword("password");

        assertValidationThrows(request, emptyOrNull("oldEmail"));
    }

    @Test
    void oldEmailNull() throws Exception {
        UserEmailUpdateRequest request = new UserEmailUpdateRequest();

        request.setNewEmail("arthur.araujo@hotmail.com");
        request.setPassword("password");

        assertValidationThrows(request, emptyOrNull("oldEmail"));
    }

    @Test
    void newEmailEmpty() throws Exception {
        UserEmailUpdateRequest request = new UserEmailUpdateRequest();

        request.setOldEmail("arthur.araujo@gmail.com");
        request.setNewEmail("");
        request.setPassword("password");

        assertValidationThrows(request, emptyOrNull("newEmail"));
    }

    @Test
    void newEmailNull() throws Exception {
        UserEmailUpdateRequest request = new UserEmailUpdateRequest();

        request.setOldEmail("arthur.araujo@gmail.com");
        request.setPassword("password");

        assertValidationThrows(request, emptyOrNull("newEmail"));
    }

    @Test
    void passwordEmpty() throws Exception {
        UserEmailUpdateRequest request = new UserEmailUpdateRequest();

        request.setOldEmail("arthur.araujo@gmail.com");
        request.setNewEmail("arthur.araujo@hotmail.com");
        request.setPassword("");

        assertValidationThrows(request, emptyOrNull("password"));
    }

    @Test
    void passwordNull() throws Exception {
        UserEmailUpdateRequest request = new UserEmailUpdateRequest();

        request.setOldEmail("arthur.araujo@gmail.com");
        request.setNewEmail("arthur.araujo@hotmail.com");

        assertValidationThrows(request, emptyOrNull("password"));
    }


    @Test
    void fieldsEmpty() throws Exception {
        UserEmailUpdateRequest request = new UserEmailUpdateRequest();

        request.setOldEmail("");
        request.setNewEmail("");
        request.setPassword("");

        assertValidationThrows(request, emptyOrNull("oldEmail"));
    }

    @Test
    void fieldsNull() throws Exception {
        assertValidationThrows(new UserEmailUpdateRequest(), emptyOrNull("oldEmail"));
    }
}
