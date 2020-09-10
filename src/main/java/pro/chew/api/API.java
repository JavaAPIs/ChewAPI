package pro.chew.api;

import org.json.JSONArray;
import org.json.JSONObject;
import pro.chew.api.errors.InternalServerError;
import pro.chew.api.errors.NotFound;
import pro.chew.api.errors.RateLimited;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class API {
    private final String baseUrl = "https://api.chew.pro/";

    public String getBaseUrl() {
        return baseUrl;
    }

    public String get(String path) {
        return get(path, null, null);
    }

    public String get(String path, Map<String, String> headers, Map<String, String> params) {
        try {
            List<CharSequence> query = new ArrayList<>();
            if(params != null) {
                for(String key : params.keySet()) {
                    try {
                        query.add(
                            URLEncoder.encode(key, StandardCharsets.UTF_8.toString())
                                + "="
                                + URLEncoder.encode(params.get(key), StandardCharsets.UTF_8.toString())
                        );
                    } catch (UnsupportedEncodingException ex) {
                        throw new RuntimeException(ex.getCause());
                    }
                }
            }
            URL url = new URL(baseUrl + path + "?" + String.join("&", query));
            // Creating a connection
            HttpURLConnection request = (HttpURLConnection)url.openConnection();
            request.setRequestProperty("User-Agent", "ChewAPI");
            if(headers != null) {
                for(String key : headers.keySet()) {
                    request.setRequestProperty(key, headers.get(key));
                }
            }
            request.connect();

            // Get response
            BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            switch(request.getResponseCode()) {
                case 400:
                    throw new IllegalArgumentException("1 or more arguments were invalid");
                case 404:
                    throw new NotFound("That object or endpoint doesn't exist!");
                case 429:
                    throw new RateLimited("You have reached the rate limit!");
                case 500:
                    throw new InternalServerError("A server side error occurred while performing this request. Please try again later!");
            }

            // Return response
            return response.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public JSONArray getAsJSONArray(String path) {
        return new JSONArray(get(path));
    }

    public JSONObject getAsJSON(String path) {
        return new JSONObject(get(path));
    }

    public JSONObject getAsJSON(String path, Map<String, String> headers, Map<String, String> params) {
        return new JSONObject(get(path, headers, params));
    }
}