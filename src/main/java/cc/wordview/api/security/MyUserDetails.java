package cc.wordview.api.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cc.wordview.api.database.entity.User;
import cc.wordview.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyUserDetails implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOpt = userRepository.findByEmail(username);

		if (!userOpt.isPresent()) {
			throw new UsernameNotFoundException("Nenhum usuario encontrado com o email especificado.");
		}

		User user = userOpt.get();

		return org.springframework.security.core.userdetails.User.withUsername(username).password(user.getPassword())
				.authorities(user.getRole()).accountExpired(false).accountLocked(false).credentialsExpired(false)
				.disabled(false).build();
	}

}