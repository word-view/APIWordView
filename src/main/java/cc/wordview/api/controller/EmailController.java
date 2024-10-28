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

package cc.wordview.api.controller;

import cc.wordview.api.Constants;
import cc.wordview.api.database.entity.Email;
import cc.wordview.api.request.RequestValidation;
import cc.wordview.api.service.specification.EmailServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static cc.wordview.api.controller.response.ExceptionHandler.response;
import static cc.wordview.api.controller.response.Response.badRequest;
import static cc.wordview.api.controller.response.Response.ok;


@RestController
@CrossOrigin(origins = Constants.CORS_ORIGIN)
@RequestMapping(path = Constants.REQUEST_PATH + "/email")
public class EmailController extends ServiceController<EmailServiceInterface> {
        private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

        @Autowired
        private EmailServiceInterface service;

        @GetMapping
        public ResponseEntity<?> addEmail(@RequestParam String email) {
                return response(() -> {
                        if (RequestValidation.invalidEmail(email)) {
                                return badRequest("Invalid email!");
                        }

                        Email emailEntity = new Email();
                        emailEntity.setEmail(email);
                        service.insert(emailEntity);

                        logger.info("A new email has arrived! (%s)".formatted(email));

                        return ok("OK!");
                });
        }
}
