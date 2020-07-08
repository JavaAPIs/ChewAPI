package pro.chew.api;

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
}
