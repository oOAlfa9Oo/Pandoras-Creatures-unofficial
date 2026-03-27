package andrews.pandoras_creatures.network;

import andrews.pandoras_creatures.util.Reference;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

/**
 * Shared payload ids that should stay stable across loader adapters.
 */
public final class PCPayloadIds {
    public static final String ANIMATION = "animation";
    public static final String BUFFLON_INVENTORY = "bufflon_inventory";
    public static final String BUFFLON_SIT = "bufflon_sit";
    public static final String BUFFLON_FOLLOW = "bufflon_follow";
    public static final String BUFFLON_COMBAT_MODE = "bufflon_combat_mode";

    private PCPayloadIds() {
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MODID, path);
    }

    public static List<String> allPaths() {
        return List.of(
                ANIMATION,
                BUFFLON_INVENTORY,
                BUFFLON_SIT,
                BUFFLON_FOLLOW,
                BUFFLON_COMBAT_MODE
        );
    }
}
