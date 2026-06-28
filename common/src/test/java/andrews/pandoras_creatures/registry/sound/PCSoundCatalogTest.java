package andrews.pandoras_creatures.registry.sound;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PCSoundCatalogTest {
    @Test
    void soundIdsAreUniqueAndUseEntityPrefix() {
        Set<String> ids = PCSoundCatalog.allSoundIds().stream().collect(Collectors.toSet());

        assertEquals(PCSoundCatalog.allSoundIds().size(), ids.size());
        assertTrue(PCSoundCatalog.allSoundIds().stream().allMatch(id -> id.startsWith("entity.")));
    }

    @Test
    void soundIdsExistInSoundsJson() throws Exception {
        try (InputStream stream = PCSoundCatalogTest.class.getResourceAsStream("/assets/pandoras_creatures/sounds.json")) {
            assertNotNull(stream, "sounds.json should be available on the test classpath");
            String soundsJson = new String(stream.readAllBytes(), StandardCharsets.UTF_8);

            for (String soundId : PCSoundCatalog.allSoundIds()) {
                assertTrue(soundsJson.contains("\"" + soundId + "\""), "Missing sound id in sounds.json: " + soundId);
            }
        }
    }
}
