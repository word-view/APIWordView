package cc.wordview.api.test.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import cc.wordview.api.Application;
import cc.wordview.api.test.api.MockValues;
import cc.wordview.api.test.api.controller.mockentity.MockWord;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class WordControllerTest {
	@Autowired
	private MockMvc request;

	// CREATE
	@Test
	void create() throws Exception {
		MockWord word = new MockWord("car", "2", MockValues.ADMIN_TOKEN);

		TestRequest.post(request, "/word/", word.toJson(), status().isCreated());
	}

	@Test
	void createByNonAdmin() throws Exception {
		MockWord word = new MockWord("car", "2", MockValues.NON_ADMIN_TOKEN);

		TestRequest.post(request, "/word/", word.toJson(), status().isForbidden());
	}

	@Test
	void createByNonExistentUser() throws Exception {
		MockWord word = new MockWord("car", "2", MockValues.INEXISTENT_TOKEN);

		TestRequest.post(request, "/word/", word.toJson(), status().isNotFound());
	}
}
