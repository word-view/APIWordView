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

package cc.wordview.api.request.user;


import cc.wordview.api.exception.RequestValidationException;
import cc.wordview.api.request.Request;
import cc.wordview.api.request.RequestValidation;
import lombok.Getter;
import lombok.Setter;

import static cc.wordview.api.request.ExceptionTemplate.emptyOrNull;
import static cc.wordview.api.request.ExceptionTemplate.invalid;
import static java.util.Objects.isNull;

@Getter
@Setter
public class UserEmailUpdateRequest implements Request {
    private String oldEmail;
    private String newEmail;
    private String password;

    public void validate() throws RequestValidationException {
        if (isNull(oldEmail) || oldEmail.isEmpty()) {
            throw emptyOrNull("oldEmail");
        }

        if (RequestValidation.invalidEmail(oldEmail)) {
            throw invalid("oldEmail");
        }

        if (isNull(newEmail) || newEmail.isEmpty()) {
            throw emptyOrNull("newEmail");
        }

        if (RequestValidation.invalidEmail(newEmail)) {
            throw invalid("newEmail");
        }

        if (oldEmail.equals(newEmail)) {
            throw new RequestValidationException("Emails cannot be equal");
        }

        if (isNull(password) || password.isEmpty()) {
            throw emptyOrNull("password");
        }
    }
}
