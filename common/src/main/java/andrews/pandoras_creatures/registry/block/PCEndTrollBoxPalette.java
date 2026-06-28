package andrews.pandoras_creatures.registry.block;

import andrews.pandoras_creatures.util.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;

import java.util.List;

public final class PCEndTrollBoxPalette {
    private static final List<DyeColor> ORDERED_COLORS = PCEndTrollBoxNaming.orderedColorNames().stream()
            .map(colorName -> DyeColor.byName(colorName, DyeColor.WHITE))
            .toList();

    private PCEndTrollBoxPalette() {
    }

    public static List<DyeColor> orderedColors() {
        return ORDERED_COLORS;
    }

    public static String blockName(DyeColor color) {
        return PCEndTrollBoxNaming.blockName(color == null ? null : color.getName());
    }

    public static DyeColor colorForBlockName(String blockName) {
        for (DyeColor color : ORDERED_COLORS) {
            if (blockName(color).equals(blockName)) {
                return color;
            }
        }

        return null;
    }

    public static String texturePath(DyeColor color) {
        return PCEndTrollBoxNaming.texturePath(color == null ? null : color.getName());
    }

    public static ResourceLocation textureId(DyeColor color) {
        return Reference.id(texturePath(color));
    }
}

