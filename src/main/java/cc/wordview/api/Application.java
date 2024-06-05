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

package cc.wordview.api;

import static cc.wordview.api.Constants.CORS_ORIGIN;
import static cc.wordview.api.Constants.CORS_ORIGIN_ALL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import cc.wordview.api.config.WordViewConfig;

@SpringBootApplication
@ComponentScan(basePackages = { "cc.wordview.api" })
public class Application implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	private WordViewConfig config;

	public static void main(String... args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		config.setProduction(args.containsOption("production"));

		if (config.isProduction()) {
			if (CORS_ORIGIN == CORS_ORIGIN_ALL) {
				logger.warn("CORS_ORIGIN is set to all.");
			}
		} else {
			logger.info("All requests to YouTube's API are being mocked");
		}
	}
}
