package andrews.pandoras_creatures.registry.item;

import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.List;

/**
 * Shared item ids that should remain stable across loader adapters.
 */
public final class PCItemIds {
    public static final String CRAB_MEAT = "crab_meat";
    public static final String CRAB_MEAT_COOKED = "crab_meat_cooked";
    public static final String SEAHORSE = "seahorse";
    public static final String SEAHORSE_COOKED = "seahorse_cooked";
    public static final String ACIDIC_ARCHVINE_TONGUE = "acidic_archvine_tongue";
    public static final String HERB_BUNDLE = "herb_bundle";
    public static final String BUFFLON_BEEF = "bufflon_beef";
    public static final String BUFFLON_BEEF_COOKED = "bufflon_beef_cooked";
    public static final String BUFFLON_HIDE = "bufflon_hide";
    public static final String BUFFLON_SADDLE = "bufflon_saddle";
    public static final String BUFFLON_PLAYER_SEATS = "bufflon_player_seats";
    public static final String BUFFLON_SMALL_STORAGE = "bufflon_small_storage";
    public static final String BUFFLON_LARGE_STORAGE = "bufflon_large_storage";
    public static final String END_TROLL_SKIN = "end_troll_skin";
    public static final String ARACHNON_HAMMER = "arachnon_hammer";
    public static final String CRAB_BUCKET = "crab_bucket";
    public static final String SEAHORSE_BUCKET = "seahorse_bucket";
    public static final String PLANT_HAT = "plant_hat";

    public static final String ARACHNON_SPAWN_EGG = spawnEggId(PCEntityIds.ARACHNON);
    public static final String HELLHOUND_SPAWN_EGG = spawnEggId(PCEntityIds.HELLHOUND);
    public static final String CRAB_SPAWN_EGG = spawnEggId(PCEntityIds.CRAB);
    public static final String SEAHORSE_SPAWN_EGG = spawnEggId(PCEntityIds.SEAHORSE);
    public static final String ACIDIC_ARCHVINE_SPAWN_EGG = spawnEggId(PCEntityIds.ACIDIC_ARCHVINE);
    public static final String BUFFLON_SPAWN_EGG = spawnEggId(PCEntityIds.BUFFLON);
    public static final String END_TROLL_SPAWN_EGG = spawnEggId(PCEntityIds.END_TROLL);

    private PCItemIds() {
    }

    public static String spawnEggId(String entityId) {
        return entityId + "_spawn_egg";
    }

    public static ResourceKey<Item> key(String path) {
        return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Reference.MODID, path));
    }

    public static List<String> allPaths() {
        return List.of(
                CRAB_MEAT,
                CRAB_MEAT_COOKED,
                SEAHORSE,
                SEAHORSE_COOKED,
                ACIDIC_ARCHVINE_TONGUE,
                HERB_BUNDLE,
                BUFFLON_BEEF,
                BUFFLON_BEEF_COOKED,
                BUFFLON_HIDE,
                BUFFLON_SADDLE,
                BUFFLON_PLAYER_SEATS,
                BUFFLON_SMALL_STORAGE,
                BUFFLON_LARGE_STORAGE,
                END_TROLL_SKIN,
                ARACHNON_HAMMER,
                CRAB_BUCKET,
                SEAHORSE_BUCKET,
                PLANT_HAT,
                ARACHNON_SPAWN_EGG,
                HELLHOUND_SPAWN_EGG,
                CRAB_SPAWN_EGG,
                SEAHORSE_SPAWN_EGG,
                ACIDIC_ARCHVINE_SPAWN_EGG,
                BUFFLON_SPAWN_EGG,
                END_TROLL_SPAWN_EGG
        );
    }
}
