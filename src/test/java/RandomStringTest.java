import org.junit.Test;
import pro.chew.api.ChewAPI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RandomStringTest {
    @Test
    public void randomTest() {
        ChewAPI api = new ChewAPI();
        String random = api.generateRandomString();
        assertNotNull(random);
        assertEquals(20, random.length());
    }

    @Test
    public void randomWithLengthTest() {
        ChewAPI api = new ChewAPI();
        String random = api.generateRandomString(50);
        assertNotNull(random);
        assertEquals(50, random.length());
    }
}
