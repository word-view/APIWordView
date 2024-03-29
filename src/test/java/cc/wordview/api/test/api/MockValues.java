package cc.wordview.api.test.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import cc.wordview.api.test.api.controller.mockentity.MockUser;

public class MockValues {
	public static String getUserJwt(MockMvc request) throws Exception {
		MockUser mock = new MockUser("mock.user@gmail.com", "S_enha64");
		MvcResult result = request
				.perform(MockMvcRequestBuilders.post("/api/v1/user/login")
						.contentType("application/json")
						.content(mock.toJson()))
				.andExpect(status().isOk()).andReturn();

		return result.getResponse().getContentAsString();
	}

	public static String getAdmJwt(MockMvc request) throws Exception {
		MockUser mock = new MockUser("mock.admin@gmail.com", "S_enha64");
		MvcResult result = request
				.perform(MockMvcRequestBuilders.post("/api/v1/user/login")
						.contentType("application/json")
						.content(mock.toJson()))
				.andExpect(status().isOk()).andReturn();

		return result.getResponse().getContentAsString();
	}
}
