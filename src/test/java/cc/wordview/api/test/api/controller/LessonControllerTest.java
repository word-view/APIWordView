package cc.wordview.api.test.api.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import cc.wordview.api.Application;
import cc.wordview.api.database.types.LessonDifficulty;
import cc.wordview.api.test.api.MockValues;
import cc.wordview.api.test.api.controller.mockentity.MockLesson;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.MethodName.class)
class LessonControllerTest {
	@Autowired
	private MockMvc request;

	// CREATE
	@Test
	void create() throws Exception {
		TestRequest.post(request, "/lesson/",
				new MockLesson("lesson1", LessonDifficulty.STARTER, MockValues.ADMIN_TOKEN).toJson(),
				status().isCreated());
	}

	@Test
	void createByNonExistentUser() throws Exception {
		TestRequest.post(request, "/lesson/",
				new MockLesson("lesson1", LessonDifficulty.STARTER, MockValues.INEXISTENT_TOKEN).toJson(),
				status().isNotFound());
	}

	@Test
	void createByNonAdmin() throws Exception {
		TestRequest.post(request, "/lesson/",
				new MockLesson("lesson1", LessonDifficulty.STARTER, MockValues.NON_ADMIN_TOKEN).toJson(),
				status().isForbidden());
	}

	// READ
	@Test
	void getByTitle() throws Exception {
		TestRequest.get(request, "/lesson/search?title=lesson", status().isOk());
	}

	@Test
	void getByNonExistentTitle() throws Exception {
		TestRequest.get(request, "/lesson/search?title=sd", status().isNotFound());
	}

	@Test
	void getByDifficulty() throws Exception {
		TestRequest.get(request, "/lesson/search?diffi=starter", status().isOk());
	}

	@Test
	void getByNonExistentDifficulty() throws Exception {
		TestRequest.get(request, "/lesson/search?diffi=w", status().isNotFound());
	}

	@Test
	void getByCategory() throws Exception {
		TestRequest.get(request, "/lesson/search?category=1", status().isOk());
	}

	@Test
	void getByZeroCategory() throws Exception {
		TestRequest.get(request, "/lesson/search?category=0", status().isBadRequest());
	}

	@Test
	void getByNonExistentCategory() throws Exception {
		TestRequest.get(request, "/lesson/search?category=2", status().isNotFound());
	}
}
