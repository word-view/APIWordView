package cc.wordview.api.service.specification;

import cc.wordview.api.response.user.NoCredentialsResponse;
import cc.wordview.api.database.entity.User;
import cc.wordview.api.exception.IncorrectCredentialsException;
import cc.wordview.api.exception.NoSuchEntryException;

import java.util.List;

public interface UserServiceInterface extends ServiceInterface<User, User> {
        List<NoCredentialsResponse> getAllUsers();

        User getByToken(String token) throws NoSuchEntryException;

        NoCredentialsResponse getByIdWithoutCredentials(Long id) throws NoSuchEntryException;

        User getByEmail(String email) throws NoSuchEntryException;

        String login(User entity) throws NoSuchEntryException, IncorrectCredentialsException;

        void delete(User entity) throws NoSuchEntryException, IncorrectCredentialsException;
}
