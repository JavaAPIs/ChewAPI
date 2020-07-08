package pro.chew.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import pro.chew.api.errors.InternalServerError;
import pro.chew.api.errors.NotFound;
import pro.chew.api.errors.RateLimited;

import java.io.IOException;

public class API {
    private static OkHttpClient client;
    public static final String baseUrl = "https://api.chew.pro/";

    public API() {
        client = new OkHttpClient();
    }

    public String get(String path) {
        Request request = new Request.Builder()
                .url(baseUrl + path)
                .get()
                .build();

        return performRequest(request);
    }

    public String performRequest(Request request) {
        try (Response response = client.newCall(request).execute()) {
            switch(response.code()) {
                case 400:
                    throw new IllegalArgumentException("1 or more arguments were invalid");
                case 404:
                    throw new NotFound("That object or endpoint doesn't exist!");
                case 429:
                    throw new RateLimited("You have reached the rate limit!");
                case 500:
                    throw new InternalServerError("A server side error occurred while performing this request. Please try again later!");
            }
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray getAsJSONArray(String path) {
        return new JSONArray(get(path));
    }

    public JSONObject getAsJSON(String path) {
        return new JSONObject(get(path));
    }
}