package cc.wordview.api.test.api.controller.mockentity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Test case workaround for get requests that require authentication
*/
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class MockAuthorizationBody extends MockEntity {
        private String authorization;
}
