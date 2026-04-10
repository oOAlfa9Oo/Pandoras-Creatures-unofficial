package andrews.pandoras_creatures.forge.registry;

import andrews.pandoras_creatures.block_entities.EndTrollBoxBlockEntity;
import andrews.pandoras_creatures.registry.block.PCBlockEntityIds;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

public final class PCForgeBlockEntities {
    private static boolean registered;

    private PCForgeBlockEntities() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(PCForgeBlockEntities::registerBlockEntities);
    }

    @SuppressWarnings("unchecked")
    public static BlockEntityType<EndTrollBoxBlockEntity> endTrollBox() {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Reference.MODID, PCBlockEntityIds.END_TROLL_BOX);
        BlockEntityType<?> value = BuiltInRegistries.BLOCK_ENTITY_TYPE.get(id);
        if (value == null) {
            throw new IllegalArgumentException("Unknown forge block entity type id: " + id);
        }
        return (BlockEntityType<EndTrollBoxBlockEntity>) value;
    }

    private static void registerBlockEntities(RegisterEvent event) {
        if (registered || !event.getRegistryKey().equals(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES)) {
            return;
        }

        registered = true;
        event.register(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES, helper -> helper.register(
                ResourceLocation.fromNamespaceAndPath(Reference.MODID, PCBlockEntityIds.END_TROLL_BOX),
                BlockEntityType.Builder.of(EndTrollBoxBlockEntity::new, PCForgeBlocks.getEndTrollBoxBlockArray()).build(null)
        ));
    }
}
