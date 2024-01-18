package cc.wordview.api.response.user;

import cc.wordview.api.database.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoCredentialsResponse {
	public Long id;
	public String username;
	public String email;

	public NoCredentialsResponse(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.email = user.getEmail();
	}
}
