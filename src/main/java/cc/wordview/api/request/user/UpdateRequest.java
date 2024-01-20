package cc.wordview.api.request.user;

import static java.util.Objects.isNull;

import cc.wordview.api.database.entity.User;
import cc.wordview.api.exception.RequestValidationException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRequest {
        public String username;
        public String email;

        public User toEntity() throws RequestValidationException {
                this.validate();

                User user = new User();

                user.setUsername(username);
                user.setEmail(email);

                return user;
        }

        private void validate() throws RequestValidationException {
                if (isNull(username) || username.isEmpty() && isNull(email) || email.isEmpty()) {
                        throw new RequestValidationException("Specify at least 1 field");
                }
        }
}
