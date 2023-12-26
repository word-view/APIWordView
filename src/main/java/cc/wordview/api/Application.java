/*
 Copyright (c) 2023 WordView

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package cc.wordview.api;

import static cc.wordview.api.Settings.CORS_ORIGIN;
import static cc.wordview.api.Settings.CORS_ORIGIN_ALL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "cc.wordview.api" })
public class Application {
        private static final Logger Logger = LoggerFactory.getLogger(Application.class);

        public static void main(String[] args) {
                if (SpringApplication.run(Application.class).isActive()) {
                        if (CORS_ORIGIN == CORS_ORIGIN_ALL)
                                Logger.warn("Allowing CORS_ORIGIN from all sources");
                }
        }
}
