package andrews.pandoras_creatures.world.structure;

import org.junit.jupiter.api.Test;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EndPrisonTemplateNeutralityTest {
    private static final String TEMPLATE =
            "data/pandoras_creatures/structure/end_prison/end_prison_body.nbt";

    @Test
    void entitiesAreSpawnedByTheSharedStructureContract() throws IOException {
        try (InputStream resource = getClass().getClassLoader().getResourceAsStream(TEMPLATE)) {
            assertNotNull(resource, "Missing End Prison body template");
            try (DataInputStream input = new DataInputStream(new GZIPInputStream(resource))) {
                assertEquals(10, input.readUnsignedByte(), "NBT root must be a compound");
                input.readUTF();
                assertTrue(rootEntitiesListIsEmpty(input),
                        "End Prison entities must be created by EndPrisonStructure, not loader-specific NBT");
            }
        }
    }

    private static boolean rootEntitiesListIsEmpty(DataInputStream input) throws IOException {
        while (true) {
            int type = input.readUnsignedByte();
            if (type == 0) {
                return false;
            }
            String name = input.readUTF();
            if (type == 9 && "entities".equals(name)) {
                input.readUnsignedByte();
                return input.readInt() == 0;
            }
            skipPayload(input, type);
        }
    }

    private static void skipPayload(DataInputStream input, int type) throws IOException {
        switch (type) {
            case 1 -> input.skipNBytes(1);
            case 2 -> input.skipNBytes(2);
            case 3, 5 -> input.skipNBytes(4);
            case 4, 6 -> input.skipNBytes(8);
            case 7 -> input.skipNBytes(input.readInt());
            case 8 -> input.readUTF();
            case 9 -> {
                int elementType = input.readUnsignedByte();
                int length = input.readInt();
                for (int index = 0; index < length; index++) {
                    skipPayload(input, elementType);
                }
            }
            case 10 -> {
                int nestedType;
                while ((nestedType = input.readUnsignedByte()) != 0) {
                    input.readUTF();
                    skipPayload(input, nestedType);
                }
            }
            case 11 -> input.skipNBytes(Math.multiplyExact(input.readInt(), Integer.BYTES));
            case 12 -> input.skipNBytes(Math.multiplyExact(input.readInt(), Long.BYTES));
            default -> throw new IOException("Unsupported NBT tag type: " + type);
        }
    }
}