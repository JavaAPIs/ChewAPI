import org.junit.Test;
import pro.chew.api.ChewAPI;

import static org.junit.Assert.*;

public class ChewSpeakTest {
    @Test
    public void chewSpeakTest() {
        ChewAPI api = new ChewAPI();
        String response = api.convertToChewSpeak("abc");
        assertNotNull(response);
        assertEquals("abac", response);
    }
}
