package cc.wordview.api.test.api.controller.mockentity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class MockUser extends MockEntity {
        private String username;
        private String email;
        private String password;

        public MockUser(String email, String password) {
                setEmail(email);
                setPassword(password);
        }
}
