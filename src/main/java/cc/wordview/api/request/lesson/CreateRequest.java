package cc.wordview.api.request.lesson;

import cc.wordview.api.exception.RequestValidationException;
import cc.wordview.api.database.entity.Lesson;

import static java.util.Objects.isNull;
import static cc.wordview.api.request.ExceptionTemplate.*;

public class CreateRequest {
        public String title;
        public String difficulty;
        public String authorization;

        public Lesson toEntity() throws RequestValidationException {
                this.validate();

                Lesson newLesson = new Lesson();

                newLesson.setTitle(title);
                newLesson.setDifficulty(difficulty);

                return newLesson;
        }

        private void validate() throws RequestValidationException {
                if (isNull(title) || title.isEmpty()) {
                        throw emptyOrNull("'title'");
                }

                if (isNull(difficulty) || difficulty.isEmpty()) {
                        throw emptyOrNull("'difficulty'");
                }

                if (isNull(authorization) || authorization.isEmpty()) {
                        throw emptyOrNull("'authorization'");
                }

        }
}
