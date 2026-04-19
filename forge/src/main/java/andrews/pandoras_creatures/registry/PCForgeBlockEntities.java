package andrews.pandoras_creatures.forge.registry;

import andrews.pandoras_creatures.block_entities.EndTrollBoxBlockEntity;
import andrews.pandoras_creatures.registry.block.PCBlockEntityIds;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import java.util.Arrays;
import java.util.Set;

public final class PCForgeBlockEntities {
    private static boolean registered;

    private PCForgeBlockEntities() {
    }

    public static void register(BusGroup modEventBus) {
        RegisterEvent.getBus(modEventBus).addListener(PCForgeBlockEntities::registerBlockEntities);
    }

    @SuppressWarnings("unchecked")
    public static BlockEntityType<EndTrollBoxBlockEntity> endTrollBox() {
        Identifier id = Identifier.fromNamespaceAndPath(Reference.MODID, PCBlockEntityIds.END_TROLL_BOX);
        BlockEntityType<?> value = BuiltInRegistries.BLOCK_ENTITY_TYPE.getValue(id);
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
                Identifier.fromNamespaceAndPath(Reference.MODID, PCBlockEntityIds.END_TROLL_BOX),
                new BlockEntityType<>(
                        EndTrollBoxBlockEntity::new,
                        Set.copyOf(Arrays.asList(PCForgeBlocks.getEndTrollBoxBlockArray()))
                )
        ));
    }
}
