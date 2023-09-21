package cc.wordview.api.test.api.controller.mockentity;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MockUser {
        private String username;
        private String email;
        private String password;

        public MockUser(String email, String password) {
                setEmail(email);
                setPassword(password);
        }

        public String toJson() {
                return new Gson().toJson(this);
        }
}
