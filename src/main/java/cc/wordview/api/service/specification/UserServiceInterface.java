package cc.wordview.api.service.specification;

import java.security.spec.InvalidKeySpecException;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import cc.wordview.api.database.entity.User;
import cc.wordview.api.exception.IncorrectCredentialsException;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.exception.ValueTakenException;
import cc.wordview.api.response.user.NoCredentialsResponse;

public interface UserServiceInterface extends ServiceInterface<User> {
	String register(User entity) throws ValueTakenException, InvalidKeySpecException;

	List<NoCredentialsResponse> getAllUsers();

	User getMe(HttpServletRequest request) throws NoSuchEntryException;

	NoCredentialsResponse getByIdWithoutCredentials(Long id) throws NoSuchEntryException;

	User getByEmail(String email) throws NoSuchEntryException;

	String login(User entity) throws NoSuchEntryException, IncorrectCredentialsException, InvalidKeySpecException;

	void delete(User entity) throws NoSuchEntryException, IncorrectCredentialsException;

}
