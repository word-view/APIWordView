package cc.wordview.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.wordview.api.database.entity.User;
import cc.wordview.api.exception.IncorrectCredentialsException;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.exception.ValueTakenException;
import cc.wordview.api.repository.UserRepository;
import cc.wordview.api.response.user.NoCredentialsResponse;
import cc.wordview.api.service.specification.UserServiceInterface;
import cc.wordview.api.service.util.HashedPassword;
import cc.wordview.api.service.util.Token;

@Service
public class UserService implements UserServiceInterface {
	@Autowired
	private UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public User getById(Long id) throws NoSuchEntryException {
		Optional<User> user = repository.findById(id);

		if (!user.isPresent()) {
			throw new NoSuchEntryException("Unable to find a user with this id");
		}

		return user.get();
	}

	public NoCredentialsResponse getByIdWithoutCredentials(Long id) throws NoSuchEntryException {
		return new NoCredentialsResponse(this.getById(id));
	}

	@Override
	public User getByEmail(String email) throws NoSuchEntryException {
		Optional<User> user = repository.findByEmail(email);

		if (!user.isPresent()) {
			throw new NoSuchEntryException("Unable to find a user with this email");
		}

		return user.get();
	}

	@Override
	@Deprecated
	public User getByToken(String token) throws NoSuchEntryException {
		Optional<User> user = repository.findByToken(token);

		if (!user.isPresent()) {
			throw new NoSuchEntryException("Unable to find a user with this token");
		}

		return user.get();
	}

	@Override
	public User insert(User entity) throws ValueTakenException {
		Optional<User> userWithSameEmail = repository.findByEmail(entity.getEmail());

		if (userWithSameEmail.isPresent()) {
			throw new ValueTakenException("The specified email is already taken");
		}

		String hashedPasswd = new HashedPassword(entity).getValue();

		entity.setPassword(hashedPasswd);
		entity.setToken(new Token(128).getValue());

		return repository.save(entity);

	}

	public String login(User entity) throws NoSuchEntryException, IncorrectCredentialsException {

		String hashedPasswd = new HashedPassword(entity).getValue();
		User user = this.getByEmail(entity.getEmail());

		if (!user.getPassword().equals(hashedPasswd)) {
			throw new IncorrectCredentialsException("Bad credentials");
		}

		return user.getToken();
	}

	@Override
	public List<NoCredentialsResponse> getAllUsers() {
		List<NoCredentialsResponse> usersFiltered = new ArrayList<>();

		for (User entry : repository.findAll()) {
			usersFiltered.add(new NoCredentialsResponse(entry));
		}

		return usersFiltered;
	}

	@Override
	public void delete(User entity) throws NoSuchEntryException, IncorrectCredentialsException {
		User userToDelete = this.getByToken(entity.getToken());
		String hashedPasswd = new HashedPassword(entity).getValue();

		if (!userToDelete.getPassword().equals(hashedPasswd)) {
			throw new IncorrectCredentialsException("Bad credentials");
		}

		repository.delete(userToDelete);
	}
}
