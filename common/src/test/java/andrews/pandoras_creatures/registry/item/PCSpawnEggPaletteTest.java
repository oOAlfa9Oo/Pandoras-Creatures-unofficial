package andrews.pandoras_creatures.registry.item;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PCSpawnEggPaletteTest {
    @Test
    void palettesExposeUniqueSpawnEggNames() {
        Set<String> names = PCSpawnEggPalette.values().stream()
                .map(PCSpawnEggPalette::itemName)
                .collect(Collectors.toSet());

        assertEquals(PCSpawnEggPalette.values().size(), names.size());
        assertTrue(names.stream().allMatch(name -> name.endsWith("_spawn_egg")));
    }

    @Test
    void palettesUseValidRgbColors() {
        assertTrue(PCSpawnEggPalette.values().stream().allMatch(PCSpawnEggPalette::usesValidRgbRange));
    }
}
