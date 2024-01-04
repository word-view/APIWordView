package cc.wordview.api.service;

import cc.wordview.api.database.entity.User;
import cc.wordview.api.response.user.NoCredentialsResponse;
import cc.wordview.api.security.HashedPassword;
import cc.wordview.api.security.Token;
import cc.wordview.api.service.specification.UserServiceInterface;
import cc.wordview.api.exception.IncorrectCredentialsException;
import cc.wordview.api.exception.NoSuchEntryException;
import cc.wordview.api.exception.ValueTakenException;
import cc.wordview.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static cc.wordview.api.service.ExceptionTemplate.*;

@Service
public class UserService extends Servicer implements UserServiceInterface {
        @Autowired
        private UserRepository repository;

        public UserService(UserRepository repository) { this.repository = repository; }

        @Override
        public User getById(Long id) throws NoSuchEntryException {
                return evaluatePresenceAndReturn(repository.findById(id), "id", id);
        }

        public NoCredentialsResponse getByIdWithoutCredentials(Long id)
                        throws NoSuchEntryException {
                return new NoCredentialsResponse(this.getById(id));
        }

        @Override
        public User getByEmail(String email) throws NoSuchEntryException {
                return evaluatePresenceAndReturn(repository.findByEmail(email), "email",
                                email);
        }

        @Override
        public User getByToken(String token) throws NoSuchEntryException {
                return evaluatePresenceAndReturn(repository.findByToken(token), "token",
                                token);
        }

        @Override
        public User insert(User entity) throws ValueTakenException {
                String hashedPasswd = new HashedPassword(entity).getValue();

                entity.setPassword(hashedPasswd);
                entity.setToken(new Token(128).getValue());

                // Check email is taken;
                Optional<User> user = repository.findByEmail(entity.getEmail());

                if (user.isPresent()) {
                        throw valueTaken(entity.getEmail());
                }
                else {
                        return repository.save(entity);
                }

        }

        public String login(User entity)
                        throws NoSuchEntryException, IncorrectCredentialsException {

                String hashedPasswd = new HashedPassword(entity).getValue();
                User user = this.getByEmail(entity.getEmail());

                if (!user.getPassword().equals(hashedPasswd))
                        throw incorrectCredentials();

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
        public void delete(User entity)
                        throws NoSuchEntryException, IncorrectCredentialsException {
                User userToDelete = this.getByToken(entity.getToken());
                String hashedPasswd = new HashedPassword(entity).getValue();

                if (!userToDelete.getPassword().equals(hashedPasswd))
                        throw incorrectCredentials();

                repository.delete(userToDelete);
        }
}
