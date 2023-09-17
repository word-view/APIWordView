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

import static cc.wordview.api.Settings.REQUEST_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                request.perform(post(REQUEST_PATH + "/word/").contentType("application/json")
                                .content("{ \"nameId\": \"car\", \"idLesson\": 1, \"authorization\": \"4e9394b4d2876b8741b10a\" }"))
                                .andExpect(status().isCreated());
        }

        @Test
        public void createByNonAdmin() throws Exception {
                request.perform(post(REQUEST_PATH + "/word/").contentType("application/json")
                                .content("{ \"nameId\": \"car\", \"idLesson\": 1, \"authorization\": \"4e9394b42d8741b10a\" }"))
                                .andExpect(status().isForbidden());
        }

        @Test
        public void createByNonExistentUser() throws Exception {
                request.perform(post(REQUEST_PATH + "/word/").contentType("application/json")
                                .content("{ \"nameId\": \"car\", \"idLesson\": 1, \"authorization\": \"4e9394b4b10a\" }"))
                                .andExpect(status().isNotFound());
        }

        @Test
        public void createEmptyNameId() throws Exception {
                request.perform(post(REQUEST_PATH + "/word/").contentType("application/json")
                                .content("{ \"idLesson\": 1, \"authorization\": \"4e9394b4d2876b8741b10a\" }"))
                                .andExpect(status().isBadRequest());
        }

        @Test
        public void createEmptyIdLesson() throws Exception {
                request.perform(post(REQUEST_PATH + "/word/").contentType("application/json")
                                .content("{ \"nameId\": \"car\", \"authorization\": \"4e9394b4d2876b8741b10a\" }"))
                                .andExpect(status().isBadRequest());
        }

}
