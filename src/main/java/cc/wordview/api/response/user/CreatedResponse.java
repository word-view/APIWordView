package cc.wordview.api.response.user;

import cc.wordview.api.database.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatedResponse {
	private Long id;
	private String token;

	public CreatedResponse(User user) {
		this.id = user.getId();
		this.token = user.getToken();
	}
}
