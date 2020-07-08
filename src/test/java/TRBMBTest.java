import org.junit.Test;
import pro.chew.api.ChewAPI;

import java.util.List;

import static org.junit.Assert.*;


public class TRBMBTest {
    @Test
    public void checkOneTest() {
        ChewAPI api = new ChewAPI();
        String trbmb = api.getTRBMBPhrase();
        assertNotNull(trbmb);
        assertTrue(trbmb.contains("That really"));
    }

    @Test
    public void checkMultipleTest() {
        ChewAPI api = new ChewAPI();
        List<String> phrases = api.getTRBMBPhrases(5);
        assertNotNull(phrases);
        assertEquals(5, phrases.size());
        assertTrue(phrases.get(0).contains("That really"));
    }
}
