package cc.wordview.api.test.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import cc.wordview.api.Application;
import cc.wordview.api.test.api.MockValues;
import cc.wordview.api.test.api.controller.mockentity.MockCategory;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class CategoryControllerTest {
	@Autowired
	private MockMvc request;

	@Test
	void create() throws Exception {
		MockCategory category = new MockCategory("TestCategory");

		String jwt = MockValues.getAdmJwt(request);

		TestRequest.post(request, "/category", category.toJson(), status().isCreated(), jwt);
	}

	@Test
	void createByNonAdmin() throws Exception {
		MockCategory category = new MockCategory("TestCategory");

		String jwt = MockValues.getUserJwt(request);

		TestRequest.post(request, "/category", category.toJson(), status().isUnauthorized(), jwt);
	}

	@Test
	void getAll() throws Exception {
		TestRequest.get(request, "/category", status().isOk());
	}
}
