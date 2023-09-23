package cc.wordview.api.request.user;

import cc.wordview.api.database.entity.User;
import cc.wordview.api.exception.RequestValidationException;
import cc.wordview.api.security.RequestValidation;
import lombok.Getter;
import lombok.Setter;

import static java.util.Objects.isNull;

import static cc.wordview.api.request.ExceptionTemplate.*;

@Getter
@Setter
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
                        throw emptyOrNull("email");
                }
                if (RequestValidation.invalidEmail(email)) {
                        throw invalid("email");
                }

                if (isNull(password) || password.isEmpty()) {
                        throw emptyOrNull("password");
                }
        }
}
