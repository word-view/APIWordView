package cc.wordview.api.test.api.request;

import cc.wordview.api.request.word.CreateRequest;
import cc.wordview.api.test.api.MockValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static cc.wordview.api.test.api.request.TestException.*;
import static cc.wordview.api.request.ExceptionTemplate.*;

class WordRequestTest {
        @Test
        void noException() throws Exception {
                assertDoesNotThrow(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setIdLesson(1L);
                        request.setNameId("car");
                        request.setAuthorization(MockValues.ADMIN_TOKEN);

                        request.toEntity();
                });
        }

        @Test
        void idLessonEmpty() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setNameId("car");
                        request.setAuthorization(MockValues.ADMIN_TOKEN);

                        request.toEntity();
                }, emptyOrNull("idLesson").getMessage());
        }

        @Test
        void nameIdEmpty() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setIdLesson(1L);
                        request.setNameId("");
                        request.setAuthorization(MockValues.ADMIN_TOKEN);

                        request.toEntity();
                }, emptyOrNull("nameId").getMessage());
        }

        @Test
        void nameIdNull() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setIdLesson(1L);
                        request.setAuthorization(MockValues.ADMIN_TOKEN);

                        request.toEntity();
                }, emptyOrNull("nameId").getMessage());
        }

        @Test
        void authorizationEmpty() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setIdLesson(1L);
                        request.setNameId("car");
                        request.setAuthorization("");

                        request.toEntity();
                }, emptyOrNull("authorization").getMessage());
        }

        @Test
        void authorizationNull() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setIdLesson(1L);
                        request.setNameId("car");

                        request.toEntity();
                }, emptyOrNull("authorization").getMessage());
        }
}
