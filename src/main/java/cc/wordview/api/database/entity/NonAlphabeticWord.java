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

package cc.wordview.api.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

/**
 * Represents all non-alphabetic words,
 * it has an additional field to the romanized version of the word.
 */
@Entity
@Data
@Table(name = "non_alphabetic_word")
public class NonAlphabeticWord implements Serializable {
    private static final long serialVersionUID = 7755915248916629355L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lang")
    private String lang;

    @Column(name = "translated_word")
    private String translatedWord;

    @Column(name = "romanized_word")
    private String romanizedWord;
}
