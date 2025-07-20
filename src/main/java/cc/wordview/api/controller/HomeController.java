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

package cc.wordview.api.controller;

import cc.wordview.api.Application;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static cc.wordview.api.controller.response.ExceptionHandler.response;
import static cc.wordview.api.controller.response.Response.ok;

@RestController
@CrossOrigin(origins = Application.CORS_ORIGIN)
@RequestMapping(path = Application.API_PATH + "/home")
public class HomeController {
    @GetMapping(produces = "application/json;charset=utf-8")
    public ResponseEntity<?> getEditorsPick() {
        return response(() -> {
            JsonArray editorsPickArray = new JsonArray();

            editorsPickArray.add(
                    new VideoEntry(
                            "ZnUEeXpxBJ0",
                            "Aquarela",
                            "Toquinho",
                            "https://i.ytimg.com/vi_webp/ZnUEeXpxBJ0/maxresdefault.webp"
                    ).toJsonObject()
            );
            editorsPickArray.add(
                    new VideoEntry(
                            "ZpT9VCUS54s",
                            "Suisei no parade",
                            "majiko",
                            "https://i.ytimg.com/vi_webp/ZpT9VCUS54s/maxresdefault.webp"
                    ).toJsonObject()
            );
            editorsPickArray.add(
                    new VideoEntry(
                            "HCTunqv1Xt4",
                            "When I'm Sixty Four",
                            "The Beatles",
                            "https://i.ytimg.com/vi_webp/HCTunqv1Xt4/maxresdefault.webp"
                    ).toJsonObject()
            );
            editorsPickArray.add(
                    new VideoEntry(
                            "9NPv4q57on8",
                            "Ano yume wo nazotte",
                            "YOASOBI",
                            "https://i.ytimg.com/vi_webp/9NPv4q57on8/maxresdefault.webp"
                    ).toJsonObject()
            );

            JsonObject responseObject = new JsonObject();
            responseObject.add("editors-pick", editorsPickArray);

            return ok(responseObject.toString());
        });
    }
}

@Getter
@Setter
@AllArgsConstructor
class VideoEntry {
    private String id;
    private String title;
    private String artist;
    private String cover;

    public JsonObject toJsonObject() {
        String jsonString = new Gson().toJson(this);
        return JsonParser.parseString(jsonString).getAsJsonObject();
    }
}