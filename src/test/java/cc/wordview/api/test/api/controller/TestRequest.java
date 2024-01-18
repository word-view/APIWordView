package cc.wordview.api.test.api.controller;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static cc.wordview.api.Constants.REQUEST_PATH;

public class TestRequest {
	public static void get(MockMvc request, String url, ResultMatcher status) throws Exception {
		request.perform(MockMvcRequestBuilders.get(REQUEST_PATH + url)).andExpect(status).andReturn();
	}

	public static void post(MockMvc request, String url, String content, ResultMatcher status) throws Exception {

		request.perform(
				MockMvcRequestBuilders.post(REQUEST_PATH + url).contentType("application/json").content(content))
				.andExpect(status);
	}
}
