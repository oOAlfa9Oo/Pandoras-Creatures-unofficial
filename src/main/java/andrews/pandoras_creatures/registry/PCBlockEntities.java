package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.block_entities.EndTrollBoxBlockEntity;
import andrews.pandoras_creatures.block_entities.PandoricShardBlockEntity;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PCBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Reference.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EndTrollBoxBlockEntity>> END_TROLL_BOX =
            BLOCK_ENTITY_TYPES.register("end_troll_shulker", () -> BlockEntityType.Builder.of(
                    EndTrollBoxBlockEntity::new,
                    PCBlocks.END_TROLL_BOX.get(),
                    PCBlocks.WHITE_END_TROLL_BOX.get(),
                    PCBlocks.ORANGE_END_TROLL_BOX.get(),
                    PCBlocks.MAGENTA_END_TROLL_BOX.get(),
                    PCBlocks.LIGHT_BLUE_END_TROLL_BOX.get(),
                    PCBlocks.YELLOW_END_TROLL_BOX.get(),
                    PCBlocks.LIME_END_TROLL_BOX.get(),
                    PCBlocks.PINK_END_TROLL_BOX.get(),
                    PCBlocks.GRAY_END_TROLL_BOX.get(),
                    PCBlocks.LIGHT_GRAY_END_TROLL_BOX.get(),
                    PCBlocks.CYAN_END_TROLL_BOX.get(),
                    PCBlocks.PURPLE_END_TROLL_BOX.get(),
                    PCBlocks.BLUE_END_TROLL_BOX.get(),
                    PCBlocks.BROWN_END_TROLL_BOX.get(),
                    PCBlocks.GREEN_END_TROLL_BOX.get(),
                    PCBlocks.RED_END_TROLL_BOX.get(),
                    PCBlocks.BLACK_END_TROLL_BOX.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PandoricShardBlockEntity>> PANDORIC_SHARD =
            BLOCK_ENTITY_TYPES.register("pandoric_shard", () -> BlockEntityType.Builder.of(
                    PandoricShardBlockEntity::new,
                    PCBlocks.PANDORIC_SHARD.get()
            ).build(null));
}
