package cc.wordview.api.test.api.controller;

import cc.wordview.api.Application;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static cc.wordview.api.Settings.REQUEST_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@AutoConfigureMockMvc
public class UserControllerTest {
        @Autowired
        private MockMvc request;

        // CREATE
        @Test
        public void create() throws Exception {
                request.perform(post(REQUEST_PATH + "/users/").contentType("application/json")
                                .content("{ \"username\": \"arthur\", \"email\": \"arthur.araujo@gmail.com\", \"password\": \"S_enha64\"}"))
                                .andExpect(status().isCreated());
        }

        @Test
        public void createNameSpecialChar() throws Exception {
                request.perform(post(REQUEST_PATH + "/users/").contentType("application/json")
                                .content("{ \"username\": \"@Ap2d_arthur\", \"email\": \"arthur.araujo@gmail.com\", \"password\": \"S_enha64\"}"))
                                .andExpect(status().isBadRequest());
        }

        @Test
        public void createNameEmpty() throws Exception {
                request.perform(post(REQUEST_PATH + "/users/").contentType("application/json")
                                .content("{ \"username\": \"\", \"email\": \"arthur.araujo@gmail.com\", \"password\": \"S_enha64\"}"))
                                .andExpect(status().isBadRequest());
        }

        @Test
        public void createNameNull() throws Exception {
                request.perform(post(REQUEST_PATH + "/users/").contentType("application/json")
                                .content("{\"email\": \"arthur.araujo@gmail.com\", \"password\": \"S_enha64\"}"))
                                .andExpect(status().isBadRequest());
        }

        @Test
        public void createEmailInvalid() throws Exception {
                request.perform(post(REQUEST_PATH + "/users/").contentType("application/json")
                                .content("{ \"username\": \"arthur\", \"email\": \"arthur.araujo@zmake2.com\", \"password\": \"S_enha64\"}"))
                                .andExpect(status().isBadRequest());
        }

        @Test
        public void createEmailEmpty() throws Exception {
                request.perform(post(REQUEST_PATH + "/users/").contentType("application/json")
                                .content("{ \"username\": \"arthur\", \"email\": \"\", \"password\": \"S_enha64\"}"))
                                .andExpect(status().isBadRequest());
        }

        @Test
        public void createEmailNull() throws Exception {
                request.perform(post(REQUEST_PATH + "/users/").contentType("application/json")
                                .content("{ \"username\": \"arthur\", \"password\": \"S_enha64\"}"))
                                .andExpect(status().isBadRequest());
        }

        @Test
        public void createPasswordEmpty() throws Exception {
                request.perform(post(REQUEST_PATH + "/users/").contentType("application/json")
                                .content("{ \"username\": \"arthur\", \"password\": \"\"}"))
                                .andExpect(status().isBadRequest());
        }

        @Test
        public void createPasswordNull() throws Exception {
                request.perform(post(REQUEST_PATH + "/users/").contentType("application/json")
                                .content("{ \"username\": \"arthur\"}"))
                                .andExpect(status().isBadRequest());
        }

        // READ
        @Test
        public void getById() throws Exception {
                request.perform(get(REQUEST_PATH + "/users/1")).andExpect(status().isOk())
                                .andExpect(content().json(
                                                "{\"id\":1,\"username\":\"Arthur\",\"email\":\"arthur.araujo@tutanota.com\"}"))
                                .andReturn();
        }

        @Test
        public void getByInexistentId() throws Exception {
                request.perform(get(REQUEST_PATH + "/users/64"))
                                .andExpect(status().isNotFound()).andReturn();
        }

        @Test
        public void login() throws Exception {
                request.perform(post(REQUEST_PATH + "/users/login")
                                .contentType("application/json")
                                .content("{ \"email\": \"arthur.araujo@gmail.com\", \"password\": \"S_enha64\" }"))
                                .andExpect(status().isOk());
        }

        @Test
        public void loginIncorrectCredntials() throws Exception {
                request.perform(post(REQUEST_PATH + "/users/login")
                                .contentType("application/json")
                                .content("{ \"email\": \"arthur.araujo@gmail.com\", \"password\": \"senha\" }"))
                                .andExpect(status().isUnauthorized());
        }

        @Test
        public void loginInvalidEmail() throws Exception {
                request.perform(post(REQUEST_PATH + "/users/login")
                                .contentType("application/json")
                                .content("{ \"email\": \"arthur.araujo\", \"password\": \"senha\" }"))
                                .andExpect(status().isBadRequest());
        }

        @Test
        public void loginInexistentEmail() throws Exception {
                request.perform(post(REQUEST_PATH + "/users/login")
                                .contentType("application/json")
                                .content("{ \"email\": \"arthur.a@hotmail.com\", \"password\": \"senha\" }"))
                                .andExpect(status().isNotFound());
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
