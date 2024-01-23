package cc.wordview.api.test.api.controller.mockentity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class MockWord extends MockEntity {
	private String name;
	private String idLesson;
	private String authorization;
}
