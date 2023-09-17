package cc.wordview.api.request.user;

import cc.wordview.api.database.entity.User;
import cc.wordview.api.exception.RequestValidationException;
import cc.wordview.api.security.RequestValidation;

import static java.util.Objects.isNull;

public class LoginRequest {
        public String email;
        public String password;

        public User toEntity() throws RequestValidationException {
                this.validate();

                User loginUser = new User();

                loginUser.setEmail(email);
                loginUser.setPassword(password);

                return loginUser;
        }

        private void validate() throws RequestValidationException {
                if (isNull(email) || email.isEmpty()) {
                        throw new RequestValidationException("Email cannot be empty or null");
                }
                if (RequestValidation.invalidEmail(email)) {
                        throw new RequestValidationException("Email is invalid");
                }

                if (isNull(password) || password.isEmpty()) {
                        throw new RequestValidationException(
                                        "Password cannot be empty or null");
                }
        }
}
