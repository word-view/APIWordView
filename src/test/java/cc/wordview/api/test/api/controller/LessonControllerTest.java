package cc.wordview.api.test.api.controller;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import cc.wordview.api.Application;
import cc.wordview.api.database.types.LessonDifficulty;
import cc.wordview.api.test.api.MockValues;
import cc.wordview.api.test.api.controller.mockentity.MockLesson;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@AutoConfigureMockMvc
public class LessonControllerTest {
        @Autowired
        private MockMvc request;

        // CREATE
        @Test
        public void create() throws Exception {
                TestRequest.post(
                        request,
                        "/lesson/",
                        new MockLesson("lesson1", LessonDifficulty.STARTER, MockValues.ADMIN_TOKEN).toJson(),
                        status().isCreated()
                );
        }

        @Test
        public void createByNonExistentUser() throws Exception {
                TestRequest.post(
                        request,
                        "/lesson/",
                        new MockLesson("lesson1", LessonDifficulty.STARTER, MockValues.INEXISTENT_TOKEN).toJson(),
                        status().isNotFound()
                );
        }

        @Test
        public void createByNonAdmin() throws Exception {
                TestRequest.post(
                        request,
                        "/lesson/",
                        new MockLesson("lesson1", LessonDifficulty.STARTER, MockValues.NON_ADMIN_TOKEN).toJson(),
                        status().isForbidden()
                );
        }

        // READ
        @Test
        public void getByTitle() throws Exception {
                TestRequest.get(request, "/lesson/search?title=lesson", status().isOk());
        }

        @Test
        public void getByNonExistentTitle() throws Exception {
                TestRequest.get(request, "/lesson/search?title=sd", status().isNotFound());
        }

        @Test
        public void getByDifficulty() throws Exception {
                TestRequest.get(request, "/lesson/search?diffi=starter", status().isOk());
        }

        @Test
        public void getByNonExistentDifficulty() throws Exception {
                TestRequest.get(request, "/lesson/search?diffi=sd", status().isNotFound());
        }

}
