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

import lombok.Getter;
import lombok.Setter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static cc.wordview.api.Constants.REQUEST_PATH;

public class ControllerTestRequester {
        @Getter
        @Setter
        private MockMvc mockMvc;

        /**
         * Performs a GET request in an API endpoint that uses JWT authentication.
         * @param url The endpoint
         * @param jwt The authentication token
         * @return The result of the request
         * @throws Exception If the request fails
         */
        public ResultActions get(String url, String jwt) throws Exception {
                return mockMvc.perform(MockMvcRequestBuilders.get(REQUEST_PATH + url).header("Authorization", "Bearer " + jwt));
        }

        /**
         * Performs a GET request in an API endpoint.
         * @param url The endpoint
         * @return The result of the request
         * @throws Exception If the request fails
         */
        public ResultActions get(String url) throws Exception {
                return mockMvc.perform(MockMvcRequestBuilders.get(REQUEST_PATH + url));
        }

        /**
         * Performs a PUT request in an API endpoint that uses JWT authentication.
         * @param url The endpoint
         * @param content The content of the request
         * @param jwt The authentication token
         * @return The result of the request
         * @throws Exception If the request fails
         */
        public ResultActions put(String url, String content, String jwt) throws Exception {
                return mockMvc.perform(MockMvcRequestBuilders.put(REQUEST_PATH + url).header("Authorization", "Bearer " + jwt).contentType("application/json").content(content));
        }

        /**
         * Performs a DELETE request in an API endpoint that uses JWT authentication.
         * @param url The endpoint
         * @param jwt The authentication token
         * @return The result of the request
         * @throws Exception If the request fails
         */
        public ResultActions delete(String url, String jwt) throws Exception {
                return mockMvc.perform(MockMvcRequestBuilders.delete(REQUEST_PATH + url).header("Authorization", "Bearer " + jwt));
        }

        /**
         * Performs a POST request in an API endpoint.
         * @param url The endpoint
         * @param content The content of the request
         * @return The result of the request
         * @throws Exception If the request fails
         */
        public ResultActions post(String url, String content) throws Exception {
                return mockMvc.perform(MockMvcRequestBuilders.post(REQUEST_PATH + url).contentType("application/json").content(content));
        }

        /**
         * Performs a POST request in an API endpoint that uses JWT authentication.
         * @param url The endpoint
         * @param content The content of the request
         * @param jwt The authentication token
         * @return The result of the request
         * @throws Exception If the request fails
         */
        public ResultActions post(String url, String content, String jwt) throws Exception {
                return mockMvc.perform(MockMvcRequestBuilders.post(REQUEST_PATH + url).header("Authorization", "Bearer " + jwt).contentType("application/json").content(content));
        }
}
