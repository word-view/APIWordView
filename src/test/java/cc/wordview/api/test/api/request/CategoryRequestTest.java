package cc.wordview.api.test.api.request;

import org.junit.jupiter.api.Test;

import cc.wordview.api.request.category.CreateRequest;
import cc.wordview.api.test.api.MockValues;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static cc.wordview.api.test.api.request.TestException.*;
import static cc.wordview.api.request.ExceptionTemplate.*;

class CategoryRequestTest {
        @Test
        void noException() throws Exception {
                assertDoesNotThrow(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setTitle("Cidade");
                        request.setAuthorization(MockValues.ADMIN_TOKEN);

                        request.toEntity();
                });
        }

        @Test
        void titleEmpty() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setTitle("");
                        request.setAuthorization(MockValues.ADMIN_TOKEN);

                        request.toEntity();
                }, emptyOrNull("title").getMessage());
        }

        @Test
        void titleNull() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setAuthorization(MockValues.ADMIN_TOKEN);

                        request.toEntity();
                }, emptyOrNull("title").getMessage());
        }

        @Test
        void authorizationEmpty() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setTitle("Cidade");
                        request.setAuthorization("");

                        request.toEntity();
                }, emptyOrNull("authorization").getMessage());
        }

        @Test
        void authorizationNull() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setTitle("Cidade");

                        request.toEntity();
                }, emptyOrNull("authorization").getMessage());
        }
}
