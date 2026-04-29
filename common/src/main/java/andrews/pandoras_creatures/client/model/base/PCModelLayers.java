package andrews.pandoras_creatures.client.model.base;

import andrews.pandoras_creatures.registry.block.PCBlockIds;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

/**
 * Registry for all model layer locations used by this mod
 */
public class PCModelLayers {
    // Entity model layers
    public static final ModelLayerLocation ARACHNON = createLocation("arachnon");
    public static final ModelLayerLocation ACIDIC_ARCHVINE = createLocation("acidic_archvine");
    public static final ModelLayerLocation BUFFLON = createLocation("bufflon");
    public static final ModelLayerLocation CRAB = createLocation("crab");
    public static final ModelLayerLocation END_TROLL = createLocation("end_troll");
    public static final ModelLayerLocation END_TROLL_BULLET = createLocation("end_troll_bullet");
    public static final ModelLayerLocation HELLHOUND = createLocation("hellhound");
    public static final ModelLayerLocation PLANT_HAT = createLocation("plant_hat");
    public static final ModelLayerLocation SEAHORSE = createLocation("seahorse");

    // Block entity model layers
    public static final ModelLayerLocation END_TROLL_BOX = createLocation(PCBlockIds.END_TROLL_BOX);
    public static final ModelLayerLocation PANDORIC_SHARD = createLocation(PCBlockIds.PANDORIC_SHARD);

    private static ModelLayerLocation createLocation(String name) {
        return new ModelLayerLocation(new ResourceLocation(Reference.MODID, name), "main");
    }

    private static ModelLayerLocation createLocation(String name, String layer) {
        return new ModelLayerLocation(new ResourceLocation(Reference.MODID, name), layer);
    }
}

