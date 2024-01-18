package cc.wordview.api.test.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import cc.wordview.api.Application;
import cc.wordview.api.test.api.MockValues;
import cc.wordview.api.test.api.controller.mockentity.MockAuthorizationBody;
import cc.wordview.api.test.api.controller.mockentity.MockLangWord;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class LangWordControllerTest {
	@Autowired
	private MockMvc request;

	@Test
	void create() throws Exception {
		TestRequest.post(request, "/langword/", new MockLangWord("Carro", "pt_BR", 1L, MockValues.ADMIN_TOKEN).toJson(),
				status().isCreated());
	}

	@Test
	void createByNonExistentUser() throws Exception {
		TestRequest.post(request, "/langword/",
				new MockLangWord("Carro", "pt_BR", 2L, MockValues.INEXISTENT_TOKEN).toJson(), status().isNotFound());
	}

	@Test
	void createByNonAdmin() throws Exception {
		TestRequest.post(request, "/langword/",
				new MockLangWord("Carro", "pt_BR", 2L, MockValues.NON_ADMIN_TOKEN).toJson(), status().isForbidden());
	}

	@Test
	void getByIdLesson() throws Exception {
		TestRequest.post(request, "/langword/search?lessonid=1&lang=pt-br",
				new MockAuthorizationBody(MockValues.NON_ADMIN_TOKEN).toJson(), status().isOk());
	}
}
