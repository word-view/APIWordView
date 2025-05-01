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

import org.junit.jupiter.api.Test;

import cc.wordview.api.request.user.UserUpdateRequest;

public class UserUpdateRequestTest extends RequestTest {
    @Test
    void updateUsername() {
        UserUpdateRequest request = new UserUpdateRequest();

        request.setUsername("arthur");

        assertValidationDoesNotThrow(request);
    }

    @Test
    void fieldsEmpty() throws Exception {
        UserUpdateRequest request = new UserUpdateRequest();

        request.setUsername("");

        assertValidationThrows(request, "Specify at least 1 field");
    }

    @Test
    void fieldsNull() throws Exception {
        assertValidationThrows(new UserUpdateRequest(), "Specify at least 1 field");
    }
}
