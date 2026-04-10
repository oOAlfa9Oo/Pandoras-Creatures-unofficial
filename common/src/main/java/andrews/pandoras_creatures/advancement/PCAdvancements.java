package andrews.pandoras_creatures.advancement;

import andrews.pandoras_creatures.util.Reference;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public final class PCAdvancements {
    public static final String FREE_THE_END_TROLL = "adventure/free_the_end_troll";
    private static final String IMPOSSIBLE_CRITERION = "impossible";

    private PCAdvancements() {
    }

    public static void award(ServerPlayer player, String path) {
        AdvancementHolder advancement = player.server.getAdvancements().get(ResourceLocation.fromNamespaceAndPath(Reference.MODID, path));
        if (advancement != null) {
            player.getAdvancements().award(advancement, IMPOSSIBLE_CRITERION);
        }
    }
}
