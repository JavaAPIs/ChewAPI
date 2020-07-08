import org.junit.Test;
import pro.chew.api.ChewAPI;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class TRBMBTest {
    @Test
    public void checkOneTest() {
        ChewAPI api = new ChewAPI();
        String trbmb = api.getTRBMBPhrase();
        assertNotNull(trbmb);
        assertTrue(trbmb.contains("That really"));
    }
}
