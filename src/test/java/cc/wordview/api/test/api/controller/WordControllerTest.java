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
import cc.wordview.api.test.api.MockValues;
import cc.wordview.api.test.api.controller.mockentity.MockWord;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@AutoConfigureMockMvc
public class WordControllerTest {
        @Autowired
        private MockMvc request;

        // CREATE
        @Test
        public void create() throws Exception {
                TestRequest.post(
                        request,
                        "/word/",
                        new MockWord("car", "1", MockValues.ADMIN_TOKEN).toJson(),
                        status().isCreated()
                );
        }

        @Test
        public void createByNonAdmin() throws Exception {
                TestRequest.post(
                        request,
                        "/word/",
                        new MockWord("car", "1", MockValues.NON_ADMIN_TOKEN).toJson(),
                        status().isForbidden()
                );
        }

        @Test
        public void createByNonExistentUser() throws Exception {
                TestRequest.post(
                        request,
                        "/word/",
                        new MockWord("car", "1", MockValues.INEXISTENT_TOKEN).toJson(),
                        status().isNotFound()
                );
        }
}
