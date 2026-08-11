package andrews.pandoras_creatures.content.block;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.block_entities.EndTrollBoxBlockEntity;
import andrews.pandoras_creatures.registry.block.PCBlockEntityIds;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxBootstrap;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.List;

public class EndTrollBoxBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final MapCodec<EndTrollBoxBlock> CODEC = simpleCodec(props -> new EndTrollBoxBlock(null, props));

    @Override
    public MapCodec<EndTrollBoxBlock> codec() {
        return CODEC;
    }

    protected static final VoxelShape FLOOR_AABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
    protected static final VoxelShape CEILING_AABB = Block.box(1.0D, 2.0D, 1.0D, 15.0D, 16.0D, 15.0D);
    protected static final VoxelShape NORTH_AABB = Block.box(1.0D, 1.0D, 2.0D, 15.0D, 15.0D, 16.0D);
    protected static final VoxelShape SOUTH_AABB = Block.box(1.0D, 1.0D, 0.0D, 15.0D, 15.0D, 14.0D);
    protected static final VoxelShape WEST_AABB = Block.box(2.0D, 1.0D, 1.0D, 16.0D, 15.0D, 15.0D);
    protected static final VoxelShape EAST_AABB = Block.box(0.0D, 1.0D, 1.0D, 14.0D, 15.0D, 15.0D);

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<Direction> FACING = DirectionalBlock.FACING;

    @Nullable
    private final DyeColor color;

    public EndTrollBoxBlock(@Nullable DyeColor color, BlockBehaviour.Properties properties) {
        super(properties);
        this.color = color;
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(WATERLOGGED, false)
                .setValue(FACING, Direction.UP));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new EndTrollBoxBlockEntity(this.color, pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(
                blockEntityType,
                PandorasCreaturesCommon.platform().registry().blockEntityType(PCBlockEntityIds.END_TROLL_BOX),
                EndTrollBoxBlockEntity::tick
        );
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        // 1.21.4: RenderShape.ENTITYBLOCK_ANIMATED se elimino (solo quedan INVISIBLE/MODEL,
        // ver RenderShape.java); el block entity renderer siempre se invoca aparte del render
        // shape del bloque, asi que el equivalente real para "sin modelo visible propio, todo
        // lo dibuja el block entity renderer" es INVISIBLE (el modelo de block/end_troll_box.json
        // no tiene "elements", solo la textura de particula).
        return RenderShape.INVISIBLE;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else if (player.isSpectator()) {
            return InteractionResult.CONSUME;
        } else {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof EndTrollBoxBlockEntity endTrollBoxBlockEntity) {
                player.openMenu(endTrollBoxBlockEntity);
                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.PASS;
            }
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState()
                .setValue(FACING, context.getClickedFace())
                .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, FACING);
    }

    @Override
    public BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos currentPos, Direction facing, BlockPos facingPos, BlockState facingState, RandomSource random) {
        if (state.getValue(WATERLOGGED)) {
            scheduledTickAccess.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return super.updateShape(state, level, scheduledTickAccess, currentPos, facing, facingPos, facingState, random);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        // Custom names are handled automatically by the BlockEntity via NBT
        super.setPlacedBy(level, pos, state, placer, stack);
    }

    @Override
    protected void affectNeighborsAfterRemoval(BlockState state, net.minecraft.server.level.ServerLevel level, BlockPos pos, boolean isMoving) {
        // Antes se dropeaba el inventario a mano en onRemove(); en 1.21.5 eso ya lo hace
        // BlockEntity#preRemoveSideEffects() automaticamente para cualquier block entity Container
        // (ver RandomizableContainerBlockEntity), asi que solo queda actualizar el redstone.
        Containers.updateNeighboursAfterDestroy(state, level, pos);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case DOWN -> CEILING_AABB;
            case SOUTH -> SOUTH_AABB;
            case NORTH -> NORTH_AABB;
            case EAST -> EAST_AABB;
            case WEST -> WEST_AABB;
            default -> FLOOR_AABB;
        };
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }

    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    @Nullable
    public DyeColor getColor() {
        return this.color;
    }

    public static Block getBlockByColor(@Nullable DyeColor color) {
        return PandorasCreaturesCommon.platform().registry().block(PCEndTrollBoxBootstrap.blockId(color));
    }

    public static ItemStack getColoredItemStack(@Nullable DyeColor color) {
        return new ItemStack(getBlockByColor(color));
    }
}
