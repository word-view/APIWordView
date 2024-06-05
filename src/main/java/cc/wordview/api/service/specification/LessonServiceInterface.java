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

package cc.wordview.api.service.specification;

import java.util.List;

import cc.wordview.api.database.entity.Lesson;
import cc.wordview.api.exception.NoSuchEntryException;

public interface LessonServiceInterface extends ServiceInterface<Lesson> {
	@Override
	Lesson getById(Long id) throws NoSuchEntryException;

	List<Lesson> getByTitle(String title) throws NoSuchEntryException;

	List<Lesson> getByDifficulty(String difficulty) throws NoSuchEntryException;

	List<Lesson> getByCategoryId(Long id) throws NoSuchEntryException;
}
