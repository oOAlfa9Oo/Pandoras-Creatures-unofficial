package andrews.pandoras_creatures.entities.hellhound;

import andrews.pandoras_creatures.util.Reference;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;

public final class HellhoundVariantCatalog {
    public static final int DEFAULT_TYPE = 1;
    public static final int WITHER_TYPE = 2;
    private static final int WITHER_VARIANT_ROLL = 12;

    private HellhoundVariantCatalog() {
    }

    public static int normalizeType(int typeId) {
        return typeId == WITHER_TYPE ? WITHER_TYPE : DEFAULT_TYPE;
    }

    public static int randomTypeId(RandomSource random) {
        return random.nextInt(WITHER_VARIANT_ROLL) == WITHER_VARIANT_ROLL - 1 ? WITHER_TYPE : DEFAULT_TYPE;
    }

    public static boolean isWitherType(int typeId) {
        return normalizeType(typeId) == WITHER_TYPE;
    }

    public static String texturePathString(int typeId) {
        return "textures/entity/hellhound/hellhound_" + normalizeType(typeId) + ".png";
    }

    public static Identifier texturePath(int typeId) {
        return Identifier.fromNamespaceAndPath(Reference.MODID, texturePathString(typeId));
    }
}
