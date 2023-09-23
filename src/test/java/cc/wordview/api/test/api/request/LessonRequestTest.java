package cc.wordview.api.test.api.request;


import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cc.wordview.api.database.types.LessonDifficulty;
import cc.wordview.api.request.lesson.CreateRequest;
import cc.wordview.api.test.api.MockValues;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static cc.wordview.api.test.api.request.TestException.*;
import static cc.wordview.api.request.ExceptionTemplate.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LessonRequestTest {

        @Test
        public void noException() throws Exception {
                assertDoesNotThrow(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setTitle("Lesson 1");
                        request.setDifficulty(LessonDifficulty.STARTER);
                        request.setAuthorization(MockValues.ADMIN_TOKEN);

                        request.toEntity();
                });
        }

        @Test
        public void titleEmpty() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setTitle("");
                        request.setDifficulty(LessonDifficulty.STARTER);
                        request.setAuthorization(MockValues.ADMIN_TOKEN);

                        request.toEntity();
                }, emptyOrNull("title").getMessage());
        }

        @Test
        public void titleNull() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setDifficulty(LessonDifficulty.STARTER);
                        request.setAuthorization(MockValues.ADMIN_TOKEN);

                        request.toEntity();
                }, emptyOrNull("title").getMessage());
        }

        @Test
        public void difficultyEmpty() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setTitle("Lesson 1");
                        // request.setDifficulty(""); cannot be empty when it is a enum
                        request.setAuthorization(MockValues.ADMIN_TOKEN);

                        request.toEntity();
                }, emptyOrNull("difficulty").getMessage());
        }

        @Test
        public void difficultyNull() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setTitle("Lesson 1");
                        request.setAuthorization(MockValues.ADMIN_TOKEN);

                        request.toEntity();
                }, emptyOrNull("difficulty").getMessage());
        }

        @Test
        public void authorizationEmpty() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setTitle("Lesson 1");
                        request.setDifficulty(LessonDifficulty.STARTER);
                        request.setAuthorization("");

                        request.toEntity();
                }, emptyOrNull("authorization").getMessage());
        }


        @Test
        public void authorizationNull() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setTitle("Lesson 1");
                        request.setDifficulty(LessonDifficulty.STARTER);

                        request.toEntity();
                }, emptyOrNull("authorization").getMessage());
        }
}
