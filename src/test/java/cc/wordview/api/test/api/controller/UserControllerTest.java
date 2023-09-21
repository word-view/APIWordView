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
                        "{ \"username\": \"arthur\", \"email\": \"arthur.araujo@gmail.com\", \"password\": \"S_enha64\"}", 
                        status().isCreated()        
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
                        "{ \"email\": \"arthur.araujo@gmail.com\", \"password\": \"S_enha64\" }", 
                        status().isOk()       
                );
        }

        @Test
        public void loginIncorrectCredentials() throws Exception {
                TestRequest.post(
                        request,
                        "/users/login", 
                        "{ \"email\": \"arthur.araujo@gmail.com\", \"password\": \"senha\" }", 
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
