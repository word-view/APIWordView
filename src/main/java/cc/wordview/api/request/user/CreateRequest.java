package cc.wordview.api.request.user;

import cc.wordview.api.database.entity.User;
import cc.wordview.api.exception.RequestValidationException;
import cc.wordview.api.security.RequestValidation;

import static java.util.Objects.isNull;
import static cc.wordview.api.request.ExceptionTemplate.*;

public class CreateRequest {
        public String username;
        public String email;
        public String password;

        public User toEntity() throws RequestValidationException {
                this.validate();

                User newUser = new User();

                newUser.setUsername(username);
                newUser.setEmail(email);
                newUser.setPassword(password);

                return newUser;
        }

        private void validate() throws RequestValidationException {
                if (isNull(username) || username.isEmpty()) {
                        throw emptyOrNull("'name'");
                }
                if (RequestValidation.hasSpecialCharacters(username)) {
                        throw specialChars("'name'");
                }

                if (isNull(email) || email.isEmpty()) {
                        throw emptyOrNull("'email'");
                }
                if (RequestValidation.invalidEmail(email)) {
                        throw invalid("'email'");
                }

                if (isNull(password) || password.isEmpty()) {
                        throw emptyOrNull("'password'");
                }

        }
}
