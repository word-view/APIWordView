package cc.wordview.api.test.api.request;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.SoftAssertionsProvider.ThrowingRunnable;

public class TestException {
	public static void assertThrows(ThrowingRunnable runnable, String expectedMessage) throws Exception {

		try {
			runnable.run();
		} catch (Exception exception) {
			String actualMessage = exception.getMessage();
			assertTrue(actualMessage.contains(expectedMessage));
		}

	}
}
