package cc.wordview.api.request.user;

import static cc.wordview.api.request.ExceptionTemplate.emptyOrNull;
import static cc.wordview.api.request.ExceptionTemplate.invalid;
import static cc.wordview.api.request.ExceptionTemplate.specialChars;
import static java.util.Objects.isNull;

import cc.wordview.api.database.entity.User;
import cc.wordview.api.exception.RequestValidationException;
import cc.wordview.api.request.RequestValidation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
			throw emptyOrNull("username");
		}
		if (RequestValidation.hasSpecialCharacters(username)) {
			throw specialChars("username");
		}

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
