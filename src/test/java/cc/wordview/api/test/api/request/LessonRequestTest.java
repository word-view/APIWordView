package cc.wordview.api.test.api.request;


import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cc.wordview.api.request.lesson.CreateRequest;

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
                        request.setDifficulty("starter");
                        request.setAuthorization("4e9394b4d2876b8741b10a");

                        request.toEntity();
                });
        }

        @Test
        public void titleEmpty() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setTitle("");
                        request.setDifficulty("starter");
                        request.setAuthorization("4e9394b4d2876b8741b10a");

                        request.toEntity();
                }, emptyOrNull("'title'").getMessage());
        }

        @Test
        public void titleNull() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setDifficulty("starter");
                        request.setAuthorization("4e9394b4d2876b8741b10a");

                        request.toEntity();
                }, emptyOrNull("'title'").getMessage());
        }

        @Test
        public void difficultyEmpty() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setTitle("Lesson 1");
                        request.setDifficulty("");
                        request.setAuthorization("4e9394b4d2876b8741b10a");

                        request.toEntity();
                }, emptyOrNull("'difficulty'").getMessage());
        }

        @Test
        public void difficultyNull() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setTitle("Lesson 1");
                        request.setAuthorization("4e9394b4d2876b8741b10a");

                        request.toEntity();
                }, emptyOrNull("'difficulty'").getMessage());
        }

        @Test
        public void authorizationEmpty() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setTitle("Lesson 1");
                        request.setDifficulty("starter");
                        request.setAuthorization("");

                        request.toEntity();
                }, emptyOrNull("'authorization'").getMessage());
        }


        @Test
        public void authorizationNull() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setTitle("Lesson 1");
                        request.setDifficulty("starter");

                        request.toEntity();
                }, emptyOrNull("'authorization'").getMessage());
        }
}
