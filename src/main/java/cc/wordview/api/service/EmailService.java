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

package cc.wordview.api.service;

import cc.wordview.api.database.entity.Email;
import cc.wordview.api.repository.EmailRepository;
import cc.wordview.api.service.specification.EmailServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailService implements EmailServiceInterface {
        @Autowired
        private EmailRepository repository;

        @Override
        public Email getById(Long id) {
                return null;
        }

        @Override
        public Email insert(Email entity) throws Exception {
                Optional<Email> existingEmail = repository.findByEmail(entity.getEmail());
                return existingEmail.orElseGet(() -> repository.save(entity));
        }
}
