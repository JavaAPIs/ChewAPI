import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import pro.chew.api.ChewAPI;

import java.util.List;

import static org.junit.Assert.*;


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
