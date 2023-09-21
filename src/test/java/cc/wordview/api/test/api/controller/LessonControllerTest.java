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
                        "{\"title\": \"lesson1\", \"difficulty\": \"starter\", \"authorization\": \"4e9394b4d2876b8741b10a\"}",
                        status().isCreated()
                );
        }

        @Test
        public void createByNonExistentUser() throws Exception {
                TestRequest.post(
                        request,
                        "/lesson/",
                        "{\"title\": \"lesson1\", \"difficulty\": \"starter\", \"authorization\": \"4e9741b10a\"}",
                        status().isNotFound()
                );
        }

        @Test
        public void createByNonAdmin() throws Exception {
                TestRequest.post(
                        request,
                        "/lesson/",
                        "{\"title\": \"lesson1\", \"difficulty\": \"starter\", \"authorization\": \"4e9394b42d8741b10a\"}",
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
