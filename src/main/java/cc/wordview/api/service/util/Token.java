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

package cc.wordview.api.service.util;

import java.util.Random;

public class Token {
	private String token;

	public Token(int length) {
		StringBuffer tokenString = new StringBuffer();

		appendRandomHexChar(tokenString, length);

		this.token = tokenString.toString();
	}

	public String getValue() {
		return this.token;
	}

	private void appendRandomHexChar(StringBuffer tokenHolder, int length) {
		for (int i = 0; i < length; i++) {
			tokenHolder.append(pickRandomDigit());
		}
	}

	private char pickRandomDigit() {
		return getPossibleDigits()[new Random().nextInt(6 * 4)];
	}

	private char[] getPossibleDigits() {
		return new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'C', 'Z', 'c', 'd', 'e', 'f', 'b', 'a',
				'H', 'B', 'F', 'M', 'L', 'R', '-', '!', '$', '*', '%', ':', '.' };
	}
}
