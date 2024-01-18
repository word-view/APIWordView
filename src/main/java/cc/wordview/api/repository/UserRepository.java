package cc.wordview.api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cc.wordview.api.database.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByToken(String token);

	Optional<User> findByEmail(String email);
}
