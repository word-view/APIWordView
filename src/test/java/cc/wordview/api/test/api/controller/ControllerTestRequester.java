/*
 * Copyright (c) 2024 Arthur Araujo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package cc.wordview.api.test.api.controller;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static cc.wordview.api.Constants.REQUEST_PATH;

public class ControllerTestRequester {
	public static void get(MockMvc request, String url, ResultMatcher status) throws Exception {
		request.perform(MockMvcRequestBuilders.get(REQUEST_PATH + url)).andExpect(status).andReturn();
	}

	public static void get(MockMvc request, String url, ResultMatcher status, String jwt) throws Exception {
		request.perform(MockMvcRequestBuilders.get(REQUEST_PATH + url).header("Authorization", "Bearer " + jwt))
				.andExpect(status).andReturn();
	}

	public static void put(MockMvc request, String url, String content, ResultMatcher status, String jwt)
			throws Exception {
		request.perform(
				MockMvcRequestBuilders.put(REQUEST_PATH + url)
						.header("Authorization", "Bearer " + jwt)
						.contentType("application/json")
						.content(content))
				.andExpect(status);
	}

	public static void delete(MockMvc request, String url, ResultMatcher status, String jwt)
			throws Exception {
		request.perform(
				MockMvcRequestBuilders.delete(REQUEST_PATH + url)
						.header("Authorization", "Bearer " + jwt))
				.andExpect(status);
	}

	public static void post(MockMvc request, String url, String content, ResultMatcher status) throws Exception {
		request.perform(
				MockMvcRequestBuilders.post(REQUEST_PATH + url).contentType("application/json")
						.content(content))
				.andExpect(status);
	}

	public static void post(MockMvc request, String url, String content, ResultMatcher status, String jwt)
			throws Exception {
		request.perform(
				MockMvcRequestBuilders.post(REQUEST_PATH + url)
						.header("Authorization", "Bearer " + jwt)
						.contentType("application/json")
						.content(content))
				.andExpect(status);
	}
}
