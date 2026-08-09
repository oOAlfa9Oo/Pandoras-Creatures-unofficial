package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.block_entities.EndTrollBoxBlockEntity;
import andrews.pandoras_creatures.registry.block.PCBlockEntityIds;
import andrews.pandoras_creatures.util.Reference;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public final class PCFabricBlockEntities {
    public static BlockEntityType<EndTrollBoxBlockEntity> END_TROLL_BOX;

    private static boolean initialized;

    private PCFabricBlockEntities() {
    }

    public static void register() {
        if (initialized) {
            return;
        }
        initialized = true;

        END_TROLL_BOX = Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(Reference.MODID, PCBlockEntityIds.END_TROLL_BOX),
                FabricBlockEntityTypeBuilder.create(EndTrollBoxBlockEntity::new, PCFabricBlocks.getEndTrollBoxBlockArray()).build(null)
        );
    }
}
