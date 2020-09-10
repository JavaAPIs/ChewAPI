package pro.chew.api;

import org.json.JSONArray;
import pro.chew.api.objects.SpigotDrama;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChewAPI {
    private static API api;

    public ChewAPI() {
        api = new API();
    }

    /**
     * Gets a TRBMB phrase. a singular phrase!
     * @return a single TRBMB phrase
     */
    public String getTRBMBPhrase() {
        return api.getAsJSONArray("trbmb").getString(0);
    }

    /**
     * Get multiple TRBMB phrases. Up to 1000.
     * @param amount an amount between 1 and 1000.
     * @return an array of TRBMB phrases
     */
    public List<String> getTRBMBPhrases(int amount) {
        Map<String, String> headers = new HashMap<>();
        headers.put("amount", String.valueOf(amount));

        List<Object> trbmb = new JSONArray(api.get("trbmb", headers, null)).toList();
        List<String> phrases = new ArrayList<>();
        for(Object phrase : trbmb)
            phrases.add((String) phrase);
        return phrases;
    }

    /**
     * Generates a phrase for a given acronym
     * @param acronym The acronym to fill
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
        Map<String, String> params = new HashMap<>();
        params.put("input", input);

        return api.getAsJSON("chewspeak", null, params).getString("output");
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
