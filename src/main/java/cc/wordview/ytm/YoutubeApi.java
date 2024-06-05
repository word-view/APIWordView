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

package cc.wordview.ytm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import cc.wordview.ytm.network.Client;
import cc.wordview.ytm.response.SearchResult;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YoutubeApi {
    private String apiKey;

    public List<SearchResult> search(String query, int maxResults) throws IOException {
        String url = Client.buildUrl(query, maxResults, apiKey);
        String response = Client.sendHttpRequest(url);

        JSONArray array = new JSONObject(response).getJSONArray("items");

        List<SearchResult> results = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            String entry = array.getJSONObject(i).toString();

            Gson gson = new Gson();
            SearchResult result = gson.fromJson(entry, SearchResult.class);

            results.add(result);
        }

        return results;
    }
}
