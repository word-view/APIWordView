package cc.wordview.api.test.api.controller.mockentity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class MockCategory extends MockEntity {
	private String title;
	private String authorization;
}
