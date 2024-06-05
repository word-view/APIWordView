/*
 * Copyright (c) 2024 Arthur Araujo
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

package cc.wordview.api.service;

import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.wordview.api.database.entity.User;
import cc.wordview.api.exception.IncorrectCredentialsException;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.exception.ValueTakenException;
import cc.wordview.api.repository.UserRepository;
import cc.wordview.api.response.user.NoCredentialsResponse;
import cc.wordview.api.security.JwtTokenProvider;
import cc.wordview.api.service.specification.UserServiceInterface;
import cc.wordview.api.service.util.HashedPassword;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {
	@Autowired
	private UserRepository repository;

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public String register(User entity) throws ValueTakenException, InvalidKeySpecException {
		Optional<User> userWithSameEmail = repository.findByEmail(entity.getEmail());

		if (userWithSameEmail.isPresent()) {
			throw new ValueTakenException("This email is already in use");
		}

		String hash = new HashedPassword(entity).getValue();

		entity.setPassword(hash);
		entity.setRole("ROLE_USER");
		repository.save(entity);

		return jwtTokenProvider.createToken(entity.getEmail(), entity.getRole());
	}

	@Override
	public User getMe(HttpServletRequest request) throws NoSuchEntryException {
		return getByEmail(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request)));
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
	public User insert(User entity) throws ValueTakenException {
		return repository.save(entity);
	}

	public String login(User entity)
			throws NoSuchEntryException, IncorrectCredentialsException, InvalidKeySpecException {
		String hashedPasswd = new HashedPassword(entity).getValue();
		User user = this.getByEmail(entity.getEmail());

		if (!user.getPassword().equals(hashedPasswd)) {
			throw new IncorrectCredentialsException("Bad credentials");
		}

		return jwtTokenProvider.createToken(entity.getEmail(), user.getRole());
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
	public void delete(User entity) {
		repository.delete(entity);
	}
}
