package pro.chew.api.objects;

import org.json.JSONObject;

/**
 * A SpigotDrama object. Contains a response and a permalink.
 * Not very exciting. sorry.
 */
public class SpigotDrama {
    private static JSONObject values = new JSONObject();

    public SpigotDrama(JSONObject input) {
        values = input;
    }

    public String getPhrase() {
        return values.getString("response");
    }

    public String getPermalink() {
        return values.getString("permalink");
    }
}
