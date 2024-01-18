package cc.wordview.api.test.api.controller.mockentity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class MockLangWord extends MockEntity {
	private String localizedWord;
	private String lang;
	private Long idWord;
	private String authorization;
}
