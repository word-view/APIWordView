package cc.wordview.api.test.api.controller;

import cc.wordview.api.Application;
import cc.wordview.api.test.api.controller.mockentity.MockUser;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.MethodName.class)
class UserControllerTest {
	@Autowired
	private MockMvc request;

	// CREATE
	@Test
	void create() throws Exception {
		TestRequest.post(request, "/users/", new MockUser("arthur", "arthur.araujo@gmail.com", "S_enha64").toJson(),
				status().isCreated());
	}

	@Test
	void createUserExistingEmail() throws Exception {
		TestRequest.post(request, "/users/",
				new MockUser("aaaaaaaa", "arthur.araujo@tutanota.com", "S_enha64").toJson(), status().isForbidden());
	}

	// READ
	@Test
	void getById() throws Exception {
		TestRequest.get(request, "/users/1", status().isOk());
	}

	@Test
	void getByInexistentId() throws Exception {
		TestRequest.get(request, "/users/64", status().isNotFound());
	}

	@Test
	void login() throws Exception {
		TestRequest.post(request, "/users/login", new MockUser("arthur.araujo@gmail.com", "S_enha64").toJson(),
				status().isOk());
	}

	@Test
	void loginIncorrectCredentials() throws Exception {
		TestRequest.post(request, "/users/login", new MockUser("arthur.araujo@gmail.com", "senha").toJson(),
				status().isUnauthorized());
	}
	// UPDATE
	// DELETE

	// @Test
	// public void deleteUser() throws Exception {
	// request.perform(delete(REQUEST_PATH +
	// "/users").contentType("application/json")
	// .content("{ \"token\": \"\", \"email\":
	// \"conta2@tutanota.com\", \"password\": \"senha\" }"))
	// .andExpect(status().isNotFound());
	// }
}
