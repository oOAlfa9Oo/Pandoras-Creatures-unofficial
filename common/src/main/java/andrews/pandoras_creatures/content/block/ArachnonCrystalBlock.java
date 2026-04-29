package andrews.pandoras_creatures.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ArachnonCrystalBlock extends FaceAttachedHorizontalDirectionalBlock implements SimpleWaterloggedBlock {
    protected static final VoxelShape CRYSTAL_NORTH_AABB = Block.box(4.0D, 4.0D, 7.0D, 12.0D, 12.0D, 16.0D);
    protected static final VoxelShape CRYSTAL_SOUTH_AABB = Block.box(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 9.0D);
    protected static final VoxelShape CRYSTAL_WEST_AABB = Block.box(7.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D);
    protected static final VoxelShape CRYSTAL_EAST_AABB = Block.box(0.0D, 4.0D, 4.0D, 9.0D, 12.0D, 12.0D);
    protected static final VoxelShape FLOOR_Z_SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 9.0D, 12.0D);
    protected static final VoxelShape FLOOR_X_SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 9.0D, 12.0D);
    protected static final VoxelShape CEILING_Z_SHAPE = Block.box(4.0D, 7.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    protected static final VoxelShape CEILING_X_SHAPE = Block.box(4.0D, 7.0D, 4.0D, 12.0D, 16.0D, 12.0D);

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public ArachnonCrystalBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(WATERLOGGED, false)
                .setValue(FACING, Direction.NORTH)
                .setValue(FACE, AttachFace.WALL));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        for (Direction direction : context.getNearestLookingDirections()) {
            FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
            BlockState blockstate;
            if (direction.getAxis() == Direction.Axis.Y) {
                blockstate = this.defaultBlockState()
                        .setValue(FACE, direction == Direction.UP ? AttachFace.CEILING : AttachFace.FLOOR)
                        .setValue(FACING, context.getHorizontalDirection())
                        .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
            } else {
                blockstate = this.defaultBlockState()
                        .setValue(FACE, AttachFace.WALL)
                        .setValue(FACING, direction.getOpposite())
                        .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
            }

            if (blockstate.canSurvive(context.getLevel(), context.getClickedPos())) {
                return blockstate;
            }
        }
        return null;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACE)) {
            case FLOOR:
                switch (state.getValue(FACING).getAxis()) {
                    case X:
                        return FLOOR_X_SHAPE;
                    case Z:
                    default:
                        return FLOOR_Z_SHAPE;
                }
            case WALL:
                switch (state.getValue(FACING)) {
                    case EAST:
                        return CRYSTAL_EAST_AABB;
                    case WEST:
                        return CRYSTAL_WEST_AABB;
                    case SOUTH:
                        return CRYSTAL_SOUTH_AABB;
                    case NORTH:
                    default:
                        return CRYSTAL_NORTH_AABB;
                }
            case CEILING:
            default:
                switch (state.getValue(FACING).getAxis()) {
                    case X:
                        return CEILING_X_SHAPE;
                    case Z:
                    default:
                        return CEILING_Z_SHAPE;
                }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACE, FACING, WATERLOGGED);
    }
}
