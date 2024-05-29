package cc.wordview.api.request.user;

import static java.util.Objects.isNull;

import cc.wordview.api.database.entity.User;
import cc.wordview.api.exception.RequestValidationException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {
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
                if (!isNull(username)) {
                        if (!username.isEmpty())
                                return;
                }

                if (!isNull(email)) {
                        if (!email.isEmpty())
                                return;
                }

                throw new RequestValidationException("Specify at least 1 field");
        }
}
