import org.junit.Test;
import pro.chew.api.ChewAPI;
import pro.chew.api.objects.SpigotDrama;

import static org.junit.Assert.*;

public class SpigotDramaTest {
    @Test
    public void spigotDramaTest() {
        ChewAPI api = new ChewAPI();
        SpigotDrama drama = api.generateSpigotDrama();
        assertNotNull(drama);
        assertTrue(drama.getPermalink().contains("drama.essentialsx"));
    }
}
