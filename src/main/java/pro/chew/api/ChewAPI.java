package pro.chew.api;

import okhttp3.Request;
import org.json.JSONArray;
import pro.chew.api.objects.SpigotDrama;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ChewAPI {
    private static API api;

    public ChewAPI() {
        api = new API();
    }

    /**
     * Gets a trbmb phrase. a singlular phrase!
     * @return a trbmb phrase
     */
    public String getTRBMBPhrase() {
        return api.getAsJSONArray("trbmb").getString(0);
    }

    /**
     * Get multiple trbmb phrases. Up to 1000.
     * @param amount an amount between 1 and 1000.
     * @return an array of phrases
     */
    public List<String> getTRBMBPhrases(int amount) {
        Request request = new Request.Builder()
                .url(api.getBaseUrl() + "trbmb")
                .addHeader("amount", String.valueOf(amount))
                .get()
                .build();

        List<Object> trbmb = new JSONArray(api.performRequest(request)).toList();
        List<String> phrases = new ArrayList<>();
        for(Object phrase : trbmb)
            phrases.add((String) phrase);
        return phrases;
    }

    /**
     * Generates a phrase for a given acronym
     * @return the acronym
     * @throws IllegalArgumentException if anything other than letters are given
     */
    public String generateAcronym(String acronym) {
        if(acronym.matches("[a-zA-Z]+"))
            return api.getAsJSON("acronym/" + acronym).getString("phrase");
        else
            throw new IllegalArgumentException("Acronym can only be letters!");
    }

    /**
     * Converts the input into ChewSpeak TM
     * @param input your input
     * @return the translated response
     */
    public String convertToChewSpeak(String input) {
        try {
            String encoded = URLEncoder.encode(input, "UTF-8");
            return api.getAsJSON("chewspeak?input=" + encoded).getString("output");
        } catch (UnsupportedEncodingException ignored) { }
        return null;
    }


    /**
     * Generates a random string of letters
     * @param length the length of the string
     * @return a random string
     */
    public String generateRandomString(int length) {
        return api.getAsJSON("random?length=" + length).getString("response");
    }

    /**
     * Generates a random string of length 20
     * @return a random string
     * @see ChewAPI#generateRandomString()
     */
    public String generateRandomString() {
        return generateRandomString(20);
    }

    /**
     * Generate some juicy spigot drama
     * @return a SpigotDrama object
     */
    public SpigotDrama generateSpigotDrama() {
        return new SpigotDrama(api.getAsJSON("spigotdrama"));
    }
}
