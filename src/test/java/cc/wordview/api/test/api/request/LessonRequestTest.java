package cc.wordview.api.test.api.request;

import org.junit.jupiter.api.Test;

import cc.wordview.api.database.types.LessonDifficulty;
import cc.wordview.api.request.lesson.CreateRequest;
import cc.wordview.api.test.api.MockValues;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static cc.wordview.api.test.api.request.TestException.*;
import static cc.wordview.api.request.ExceptionTemplate.*;

class LessonRequestTest {

        @Test
        void noException() throws Exception {
                assertDoesNotThrow(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setTitle("Lesson 1");
                        request.setDifficulty(LessonDifficulty.STARTER);
                        request.setAuthorization(MockValues.ADMIN_TOKEN);

                        request.toEntity();
                });
        }

        @Test
        void titleEmpty() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setTitle("");
                        request.setDifficulty(LessonDifficulty.STARTER);
                        request.setAuthorization(MockValues.ADMIN_TOKEN);

                        request.toEntity();
                }, emptyOrNull("title").getMessage());
        }

        @Test
        void titleNull() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setDifficulty(LessonDifficulty.STARTER);
                        request.setAuthorization(MockValues.ADMIN_TOKEN);

                        request.toEntity();
                }, emptyOrNull("title").getMessage());
        }

        @Test
        void difficultyEmpty() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setTitle("Lesson 1");
                        request.setDifficulty("");
                        request.setAuthorization(MockValues.ADMIN_TOKEN);

                        request.toEntity();
                }, emptyOrNull("difficulty").getMessage());
        }

        @Test
        void difficultyNull() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setTitle("Lesson 1");
                        request.setAuthorization(MockValues.ADMIN_TOKEN);

                        request.toEntity();
                }, emptyOrNull("difficulty").getMessage());
        }

        @Test
        void authorizationEmpty() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setTitle("Lesson 1");
                        request.setDifficulty(LessonDifficulty.STARTER);
                        request.setAuthorization("");

                        request.toEntity();
                }, emptyOrNull("authorization").getMessage());
        }

        @Test
        void authorizationNull() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setTitle("Lesson 1");
                        request.setDifficulty(LessonDifficulty.STARTER);

                        request.toEntity();
                }, emptyOrNull("authorization").getMessage());
        }
}
