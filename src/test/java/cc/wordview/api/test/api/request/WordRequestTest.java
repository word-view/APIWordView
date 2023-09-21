package cc.wordview.api.test.api.request;

import org.junit.Test;

import cc.wordview.api.request.word.CreateRequest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static cc.wordview.api.test.api.request.TestException.*;
import static cc.wordview.api.request.ExceptionTemplate.*;

public class WordRequestTest {
        @Test
        public void noException() throws Exception {
                assertDoesNotThrow(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setIdLesson(1L);
                        request.setNameId("car");
                        request.setAuthorization("4e9394b4d2876b8741b10a");

                        request.toEntity();
                });
        }

        @Test
        public void idLessonEmpty() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setNameId("car");
                        request.setAuthorization("4e9394b4d2876b8741b10a");

                        request.toEntity();
                }, emptyOrNull("'idLesson'").getMessage());
        }

        @Test
        public void nameIdEmpty() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setIdLesson(1L);
                        request.setNameId("");
                        request.setAuthorization("4e9394b4d2876b8741b10a");

                        request.toEntity();
                }, emptyOrNull("'nameId'").getMessage());
        }

        @Test
        public void nameIdNull() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setIdLesson(1L);
                        request.setAuthorization("4e9394b4d2876b8741b10a");

                        request.toEntity();
                }, emptyOrNull("'nameId'").getMessage());
        }

        @Test
        public void authorizationEmpty() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setIdLesson(1L);
                        request.setNameId("car");
                        request.setAuthorization("");

                        request.toEntity();
                }, emptyOrNull("'authorization'").getMessage());
        }

        @Test
        public void authorizationNull() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setIdLesson(1L);
                        request.setNameId("car");

                        request.toEntity();
                }, emptyOrNull("'authorization'").getMessage());
        }
}
