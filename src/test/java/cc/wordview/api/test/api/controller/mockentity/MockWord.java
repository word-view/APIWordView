package cc.wordview.api.test.api.controller.mockentity;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MockWord {
        private String nameId;
        private String idLesson;
        private String authorization;

        public String toJson() {
                return new Gson().toJson(this);
        }
}
