package cc.wordview.api.request.user;

import static cc.wordview.api.request.ExceptionTemplate.emptyOrNull;
import static java.util.Objects.isNull;

import cc.wordview.api.database.entity.User;
import cc.wordview.api.exception.RequestValidationException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDeleteRequest {
	// public String token;
	public String password;
	public String email;

	public User toEntity() throws RequestValidationException {
		this.validate();

		User newUser = new User();

		// newUser.setToken(token);
		newUser.setEmail(email);
		newUser.setPassword(password);

		return newUser;
	}

	private void validate() throws RequestValidationException {
		// if (isNull(token) || token.isEmpty()) {
		// throw emptyOrNull("token");
		// }

		if (isNull(email) || email.isEmpty()) {
			throw emptyOrNull("email");
		}

		if (isNull(password) || password.isEmpty()) {
			throw emptyOrNull("password");
		}
	}
}
