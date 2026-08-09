package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.block_entities.EndTrollBoxBlockEntity;
import andrews.pandoras_creatures.block_entities.PandoricShardBlockEntity;
import andrews.pandoras_creatures.registry.block.PCBlockEntityIds;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PCBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Reference.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EndTrollBoxBlockEntity>> END_TROLL_BOX =
            BLOCK_ENTITY_TYPES.register(PCBlockEntityIds.END_TROLL_BOX, () -> new BlockEntityType<>(
                    EndTrollBoxBlockEntity::new,
                    PCBlocks.getEndTrollBoxBlockArray()
            ));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PandoricShardBlockEntity>> PANDORIC_SHARD =
            BLOCK_ENTITY_TYPES.register(PCBlockEntityIds.PANDORIC_SHARD, () -> new BlockEntityType<>(
                    PandoricShardBlockEntity::new,
                    PCBlocks.PANDORIC_SHARD.get()
            ));
}
