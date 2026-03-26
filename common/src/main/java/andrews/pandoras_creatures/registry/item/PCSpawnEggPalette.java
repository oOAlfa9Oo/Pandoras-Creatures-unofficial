package andrews.pandoras_creatures.registry.item;

import java.util.List;

public record PCSpawnEggPalette(String entityName, int primaryColor, int secondaryColor) {
    public static final PCSpawnEggPalette ARACHNON = new PCSpawnEggPalette("arachnon", 5394534, 12257023);
    public static final PCSpawnEggPalette HELLHOUND = new PCSpawnEggPalette("hellhound", 0xf5f3f0, 0xfc750d);
    public static final PCSpawnEggPalette CRAB = new PCSpawnEggPalette("crab", 0xf79811, 0xffde3b);
    public static final PCSpawnEggPalette SEAHORSE = new PCSpawnEggPalette("seahorse", 0x38d1d1, 0xd98f27);
    public static final PCSpawnEggPalette ACIDIC_ARCHVINE = new PCSpawnEggPalette("acidic_archvine", 0x14661f, 0x7b34ad);
    public static final PCSpawnEggPalette BUFFLON = new PCSpawnEggPalette("bufflon", 0x4f3914, 0x1a1d29);
    public static final PCSpawnEggPalette END_TROLL = new PCSpawnEggPalette("end_troll", 0x2a234d, 0x4db4bf);

    private static final List<PCSpawnEggPalette> VALUES = List.of(
            ARACHNON,
            HELLHOUND,
            CRAB,
            SEAHORSE,
            ACIDIC_ARCHVINE,
            BUFFLON,
            END_TROLL
    );

    public String itemName() {
        return entityName + "_spawn_egg";
    }

    public boolean usesValidRgbRange() {
        return isRgb24(primaryColor) && isRgb24(secondaryColor);
    }

    public static List<PCSpawnEggPalette> values() {
        return VALUES;
    }

    private static boolean isRgb24(int color) {
        return color >= 0x000000 && color <= 0xFFFFFF;
    }
}
