/*
 * Copyright (c) 2025 Arthur Araujo
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

import cc.wordview.gengolex.languages.japanese.JapaneseKanjiStrategy;
import cc.wordview.gengolex.languages.japanese.JapaneseTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Objects;

@SpringBootApplication
@ComponentScan(basePackages = {"cc.wordview.api"})
public class Application {
        private static final Logger logger = LoggerFactory.getLogger(Application.class);

        public static final String API_PATH = "/api/v1";
        public static final String CORS_ORIGIN = "*";

        private static final String RUN_DIR = Application.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        public static void main(String... args) {
                if (args.length > 0 && Objects.equals(args[0], "--prod"))
                        System.setProperty("spring.profiles.active", "prod");
                else
                        System.setProperty("spring.profiles.active", "dev");

                dealWithJar();

                JapaneseTokenizer.INSTANCE.setKanjiStrategy(JapaneseKanjiStrategy.PREFER_PARENT);
                SpringApplication.run(Application.class, args);
        }

        private static void dealWithJar() {
                if (RUN_DIR.startsWith("nested:/") && RUN_DIR.contains(".jar")) {
                        logger.warn("""
                                
                                
                                
                                ██╗    ██╗ █████╗ ██████╗ ███╗   ██╗██╗███╗   ██╗ ██████╗
                                ██║    ██║██╔══██╗██╔══██╗████╗  ██║██║████╗  ██║██╔════╝
                                ██║ █╗ ██║███████║██████╔╝██╔██╗ ██║██║██╔██╗ ██║██║  ███╗
                                ██║███╗██║██╔══██║██╔══██╗██║╚██╗██║██║██║╚██╗██║██║   ██║
                                ╚███╔███╔╝██║  ██║██║  ██║██║ ╚████║██║██║ ╚████║╚██████╔╝
                                 ╚══╝╚══╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝╚═╝  ╚═══╝ ╚═════╝
                                **************************************************************
                                *  RUNNING FROM JAR CAN CAUSE ISSUES WHEN USING CLASSPATH    *
                                *  DIRECTORIES FOR RESOURCES. IF YOU EXPERIENCE A CRASH      *
                                *  THAT IS LIKELY TO BE THE REASON                           *
                                *                                                            *
                                *  YOU SHOULD IDEALLY USE A SEPARATE FOLDER FOR RESOURCES    *
                                *  (e.g /etc/wordview/)                                      *
                                **************************************************************
                                """);
                }
        }
}
