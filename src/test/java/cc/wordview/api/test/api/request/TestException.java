package cc.wordview.api.test.api.request;

import org.junit.Assert;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.function.ThrowingRunnable;

import cc.wordview.api.exception.RequestValidationException;

public class TestException {
        public static void assertThrows(ThrowingRunnable runnable, String expectedMessage) throws Exception {
                Exception exception = 
                        Assert.assertThrows(RequestValidationException.class, runnable);

                String actualMessage = exception.getMessage();

                assertTrue(actualMessage.contains(expectedMessage));

        }
}
