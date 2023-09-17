package cc.wordview.api.repository;

import cc.wordview.api.database.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
        Optional<User> findByToken(String token);

        Optional<User> findByEmail(String email);
}
