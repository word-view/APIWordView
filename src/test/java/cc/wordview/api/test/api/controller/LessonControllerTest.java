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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                request.perform(post(REQUEST_PATH + "/lesson/").contentType("application/json")
                                .content("{\"title\": \"lesson1\", \"difficulty\": \"starter\", \"authorization\": \"4e9394b4d2876b8741b10a\"}"))
                                .andExpect(status().isCreated());
        }

        @Test
        public void createByNonExistentUser() throws Exception {
                request.perform(post(REQUEST_PATH + "/lesson/").contentType("application/json")
                                .content("{\"title\": \"lesson1\", \"difficulty\": \"starter\", \"authorization\": \"4e9741b10a\"}"))
                                .andExpect(status().isNotFound());
        }

        @Test
        public void createByNonAdmin() throws Exception {
                request.perform(post(REQUEST_PATH + "/lesson/").contentType("application/json")
                                .content("{\"title\": \"lesson1\", \"difficulty\": \"starter\", \"authorization\": \"4e9394b42d8741b10a\"}"))
                                .andExpect(status().isForbidden());
        }

        @Test
        public void createNullTitle() throws Exception {
                request.perform(post(REQUEST_PATH + "/lesson/").contentType("application/json")
                                .content("{\"difficulty\": \"starter\", \"authorization\": \"4e9394b4d2876b8741b10a\"}"))
                                .andExpect(status().isBadRequest());
        }

        @Test
        public void createNullDifficulty() throws Exception {
                request.perform(post(REQUEST_PATH + "/lesson/").contentType("application/json")
                                .content("{\"title\": \"lesson1\", \"authorization\": \"4e9394b4d2876b8741b10a\"}"))
                                .andExpect(status().isBadRequest());
        }

        // READ
        @Test
        public void getByTitle() throws Exception {
                request.perform(get(REQUEST_PATH + "/lesson/search?title=lesson"))
                                .andExpect(status().isOk());
        }

        @Test
        public void getByNonExistentTitle() throws Exception {
                request.perform(get(REQUEST_PATH + "/lesson/search?title=sd"))
                                .andExpect(status().isNotFound());
        }

        @Test
        public void getByDifficulty() throws Exception {
                request.perform(get(REQUEST_PATH + "/lesson/search?diffi=starter"))
                                .andExpect(status().isOk());
        }

        @Test
        public void getByNonExistentDifficulty() throws Exception {
                request.perform(get(REQUEST_PATH + "/lesson/search?diffi=sd"))
                                .andExpect(status().isNotFound());
        }

}
