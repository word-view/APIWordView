package cc.wordview.ytm.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client {
    private static final String BASE_URL = "https://www.googleapis.com/youtube/v3/search";

    public static String buildUrl(String query, int maxResults, String apiKey) {
        return String.format("%s?part=snippet&type=video&q=%s&maxResults=%d&key=%s", BASE_URL, query, maxResults,
                apiKey);
    }

    public static String sendHttpRequest(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set request method
        connection.setRequestMethod("GET");

        // Get response code
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Read response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            return response.toString();
        } else {
            throw new IOException("HTTP request failed with response code: " + responseCode);
        }
    }
}
