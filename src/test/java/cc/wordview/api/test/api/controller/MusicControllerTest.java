package cc.wordview.api.test.api.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import cc.wordview.api.Application;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.MethodName.class)
class MusicControllerTest {
        @Autowired
        private MockMvc request;

        @Test
        void history() throws Exception {
                TestRequest.get(request, "/music/history", status().isOk());
        }

        @Test
        void lyrics() throws Exception {
                TestRequest.get(request, "/music/lyrics?id=sAuEeM_6zpk&lang=ja", status().isOk());
        }

        @Test
        void search() throws Exception {
                TestRequest.get(request, "/music/search?q=pandora101", status().isOk());
        }

        @Test
        void download() throws Exception {
                TestRequest.get(request, "/music/download?id=KEg6FXrvHys", status().isOk());
        }
}
