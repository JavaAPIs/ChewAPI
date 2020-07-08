import org.junit.Test;
import pro.chew.api.ChewAPI;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;


public class AcronymTest {
    @Test
    public void acronymTest() {
        ChewAPI api = new ChewAPI();
        String acronym = api.generateAcronym("bob");
        assertNotNull(acronym);
    }

    @Test
    public void invalidInputTest() {
        ChewAPI api = new ChewAPI();
        assertThrows(IllegalArgumentException.class, () -> {
            api.generateAcronym("bob1234BRUH");
        });
    }
}
