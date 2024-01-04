package cc.wordview.api.test.api.request;

import org.junit.jupiter.api.Test;

import cc.wordview.api.request.langword.CreateRequest;
import cc.wordview.api.test.api.MockValues;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static cc.wordview.api.test.api.request.TestException.*;
import static cc.wordview.api.request.ExceptionTemplate.*;

class LangWordRequestTest {
        @Test
        void noException() throws Exception {
                assertDoesNotThrow(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setLocalizedWord("Carro");
                        request.setIdWord(1L);
                        request.setLang("pt_BR");
                        request.setAuthorization(MockValues.ADMIN_TOKEN);

                        request.toEntity();
                });
        }

        @Test
        void localizedWordEmpty() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setLocalizedWord("");
                        request.setIdWord(1L);
                        request.setLang("pt_BR");
                        request.setAuthorization(MockValues.ADMIN_TOKEN);

                        request.toEntity();
                }, emptyOrNull("localizedWord").getMessage());
        }

        @Test
        void localizedWordNull() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setIdWord(1L);
                        request.setLang("pt_BR");
                        request.setAuthorization(MockValues.ADMIN_TOKEN);

                        request.toEntity();
                }, emptyOrNull("localizedWord").getMessage());
        }

        @Test
        void idWordNull() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setLocalizedWord("Carro");
                        request.setLang("pt_BR");
                        request.setAuthorization(MockValues.ADMIN_TOKEN);

                        request.toEntity();
                }, emptyOrNull("idWord").getMessage());
        }

        @Test
        void langEmpty() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setLocalizedWord("Carro");
                        request.setIdWord(1L);
                        request.setLang("");
                        request.setAuthorization(MockValues.ADMIN_TOKEN);

                        request.toEntity();
                }, emptyOrNull("lang").getMessage());
        }

        @Test
        void langNull() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setLocalizedWord("Carro");
                        request.setIdWord(1L);
                        request.setAuthorization(MockValues.ADMIN_TOKEN);

                        request.toEntity();
                }, emptyOrNull("lang").getMessage());
        }

        @Test
        void authorizationEmpty() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setLocalizedWord("Carro");
                        request.setIdWord(1L);
                        request.setLang("pt_BR");
                        request.setAuthorization("");

                        request.toEntity();
                }, emptyOrNull("authorization").getMessage());
        }

        @Test
        void authorizationNull() throws Exception {
                assertThrows(() -> {
                        CreateRequest request = new CreateRequest();

                        request.setLocalizedWord("Carro");
                        request.setIdWord(1L);
                        request.setLang("pt_BR");

                        request.toEntity();
                }, emptyOrNull("authorization").getMessage());
        }
}
