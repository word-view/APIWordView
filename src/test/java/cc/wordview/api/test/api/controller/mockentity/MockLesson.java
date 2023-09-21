package cc.wordview.api.test.api.controller.mockentity;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MockLesson {
        private String title;
        private String difficulty;
        private String authorization;

        public String toJson() {
                return new Gson().toJson(this);
        }
}
