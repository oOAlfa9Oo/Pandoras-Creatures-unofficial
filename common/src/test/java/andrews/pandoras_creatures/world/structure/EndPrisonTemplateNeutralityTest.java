package andrews.pandoras_creatures.world.structure;

import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EndPrisonTemplateNeutralityTest {
    private static final String TEMPLATE =
            "data/pandoras_creatures/structures/end_prison/end_prison_body.nbt";

    @Test
    void entitiesAreSpawnedByTheSharedStructureContract() throws IOException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(TEMPLATE)) {
            assertNotNull(input, "Missing End Prison body template");
            var root = NbtIo.readCompressed(input);
            ListTag entities = root.getList("entities", Tag.TAG_COMPOUND);
            assertEquals(0, entities.size(),
                    "End Prison entities must be created by EndPrisonStructure, not loader-specific NBT");
        }
    }
}
