package cc.wordview.api.test.api.controller;

import cc.wordview.api.Application;
import cc.wordview.api.test.api.MockValues;
import cc.wordview.api.test.api.controller.mockentity.MockUser;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles("test")
class UserControllerTest {
	@Autowired
	private MockMvc request;

	@Test
	@Order(1)
	void create() throws Exception {
		MockUser user = new MockUser("arthur", "arthur.araujo@gmail.com", "S_enha64");

		TestRequest.post(request, "/user/register", user.toJson(), status().isCreated());
	}

	@Test
	@Order(2)
	void createUserExistingEmail() throws Exception {
		MockUser user = new MockUser("aaaaaaaa", "arthur.araujo@tutanota.com", "S_enha64");

		TestRequest.post(request, "/user/register", user.toJson(), status().isForbidden());
	}

	@Test
	@Order(3)
	void getById() throws Exception {
		TestRequest.get(request, "/user/1", status().isOk());
	}

	@Test
	@Order(4)
	void getMe() throws Exception {
		String jwt = MockValues.getUserJwt(request);
		TestRequest.get(request, "/user/me", status().isOk(), jwt);
	}

	@Test
	@Order(5)
	void getByInexistentId() throws Exception {
		TestRequest.get(request, "/user/64", status().isNotFound());
	}

	@Test
	@Order(6)
	void login() throws Exception {
		MockUser user = new MockUser("arthur.araujo@gmail.com", "S_enha64");

		TestRequest.post(request, "/user/login", user.toJson(), status().isOk());
	}

	@Test
	@Order(7)
	void loginIncorrectCredentials() throws Exception {
		MockUser user = new MockUser("arthur.araujo@gmail.com", "senha");

		TestRequest.post(request, "/user/login", user.toJson(), status().isUnauthorized());
	}

	@Test
	@Order(8)
	void updateMeUsername() throws Exception {
		String jwt = MockValues.getUserJwt(request);
		TestRequest.put(request, "/user/me", "{\"username\": \"uuu\"}", status().isOk(), jwt);
	}

	@Test
	@Order(10)
	void deleteMe() throws Exception {
		String jwt = MockValues.getUserJwt(request);
		TestRequest.delete(request, "/user/me", status().isOk(), jwt);
	}
}
