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

public class Constants {
	public static final String API_VERSION = "v1";
	public static final String REQUEST_PATH = "/api/" + API_VERSION;

	public static final String CORS_ORIGIN_ALL = "*";
	public static final String CORS_ORIGIN_APP = "https://app.wordview.cc";

	public static final String CORS_ORIGIN = CORS_ORIGIN_ALL;
}
