package cc.wordview.api.test.api.controller;

import cc.wordview.api.Application;
import cc.wordview.api.test.api.controller.mockentity.MockUser;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
                TestRequest.post(
                        request,
                        "/users/",
                        new MockUser("arthur", "arthur.araujo@gmail.com", "S_enha64").toJson(),
                        status().isCreated()
                );
        }

        @Test
        public void createUserExistingEmail() throws Exception {
                TestRequest.post(
                        request,
                        "/users/",
                        new MockUser("aaaaaaaa", "arthur.araujo@tutanota.com", "S_enha64").toJson(),
                        status().isForbidden()
                );
        }

        // READ
        @Test
        public void getById() throws Exception {
                TestRequest.get(request, "/users/1", status().isOk());
        }

        @Test
        public void getByInexistentId() throws Exception {
                TestRequest.get(request, "/users/64", status().isNotFound());
        }

        @Test
        public void login() throws Exception {
                TestRequest.post(
                        request,
                        "/users/login",
                        new MockUser("arthur.araujo@gmail.com", "S_enha64").toJson(),
                        status().isOk()
                );
        }

        @Test
        public void loginIncorrectCredentials() throws Exception {
                TestRequest.post(
                        request,
                        "/users/login",
                        new MockUser("arthur.araujo@gmail.com", "senha").toJson(),
                        status().isUnauthorized()
                );
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
