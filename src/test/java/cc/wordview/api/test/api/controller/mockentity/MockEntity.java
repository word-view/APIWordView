package cc.wordview.api.test.api.controller.mockentity;

import com.google.gson.Gson;

public abstract class MockEntity {
	public String toJson() {
		return new Gson().toJson(this);
	}
}
