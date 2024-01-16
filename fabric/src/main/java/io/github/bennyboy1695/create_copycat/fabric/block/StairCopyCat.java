package io.github.bennyboy1695.create_copycat.fabric.block;

import com.simibubi.create.content.decoration.copycat.WaterloggedCopycatBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import static io.github.bennyboy1695.create_copycat.fabric.util.Shapes.*;

public class StairCopyCat extends WaterloggedCopycatBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;
    public static final EnumProperty<StairsShape> STAIR_SHAPE = BlockStateProperties.STAIRS_SHAPE;
    public static final BooleanProperty CONNECTS = CustomBlockProperties.CONNECTS;

    public StairCopyCat(Properties pProperties) {
        super(pProperties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH).setValue(HALF, Half.BOTTOM).setValue(STAIR_SHAPE, StairsShape.STRAIGHT).setValue(CONNECTS, true));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder.add(FACING).add(HALF).add(STAIR_SHAPE).add(CONNECTS));
    }

    @Override
    public VoxelShape getShape(BlockState arg, BlockGetter arg2, BlockPos arg3, CollisionContext arg4) {
        return (arg.getValue(HALF) == Half.TOP ? TOP_SHAPES : BOTTOM_SHAPES)[SHAPE_BY_STATE[this.getShapeIndex(arg)]];
    }

    public int getShapeIndex(BlockState arg) {
        return arg.getValue(STAIR_SHAPE).ordinal() * 4 + arg.getValue(FACING).get2DDataValue();
    }

    public BlockState getStateForPlacement(BlockPlaceContext arg) {
        Direction direction = arg.getClickedFace();
        BlockPos blockpos = arg.getClickedPos();
        FluidState fluidstate = arg.getLevel().getFluidState(blockpos);
        BlockState blockstate = this.defaultBlockState().setValue(FACING, arg.getHorizontalDirection()).setValue(HALF, direction == Direction.DOWN || direction != Direction.UP && arg.getClickLocation().y - (double)blockpos.getY() > 0.5 ? Half.TOP : Half.BOTTOM).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
        return blockstate.setValue(STAIR_SHAPE, getStairsShape(blockstate, arg.getLevel(), blockpos));
    }

    private static StairsShape getStairsShape(BlockState arg, BlockGetter arg2, BlockPos arg3) {
        Direction direction = (Direction)arg.getValue(FACING);
        BlockState blockstate = arg2.getBlockState(arg3.relative(direction));
        if (isStairs(blockstate) && arg.getValue(HALF) == blockstate.getValue(HALF)) {
            Direction direction1 = (Direction)blockstate.getValue(FACING);
            if (direction1.getAxis() != ((Direction)arg.getValue(FACING)).getAxis() && canTakeShape(arg, arg2, arg3, direction1.getOpposite())) {
                if (direction1 == direction.getCounterClockWise()) {
                    return StairsShape.OUTER_LEFT;
                }

                return StairsShape.OUTER_RIGHT;
            }
        }

        BlockState blockstate1 = arg2.getBlockState(arg3.relative(direction.getOpposite()));
        if (isStairs(blockstate1) && arg.getValue(HALF) == blockstate1.getValue(HALF)) {
            Direction direction2 = (Direction)blockstate1.getValue(FACING);
            if (direction2.getAxis() != ((Direction)arg.getValue(FACING)).getAxis() && canTakeShape(arg, arg2, arg3, direction2)) {
                if (direction2 == direction.getCounterClockWise()) {
                    return StairsShape.INNER_LEFT;
                }

                return StairsShape.INNER_RIGHT;
            }
        }

        return StairsShape.STRAIGHT;
    }

    private static boolean canTakeShape(BlockState arg, BlockGetter arg2, BlockPos arg3, Direction arg4) {
        BlockState blockstate = arg2.getBlockState(arg3.relative(arg4));
        return !isStairs(blockstate) || blockstate.getValue(FACING) != arg.getValue(FACING) || blockstate.getValue(HALF) != arg.getValue(HALF);
    }

    public static boolean isStairs(BlockState arg) {
        return arg.getBlock() instanceof StairCopyCat;
    }

    @Override
    public boolean canConnectTexturesToward(BlockAndTintGetter reader, BlockPos fromPos, BlockPos toPos, BlockState state) {
        if (!state.getValue(CONNECTS)) return false;
        Direction facing = state.getValue(FACING);
        BlockState toState = reader.getBlockState(toPos);
        return !toState.hasProperty(CONNECTS) || toState.getValue(CONNECTS);
    }

    public static boolean isOccluded(BlockState state, BlockState other, Direction pDirection) {
        state = state.setValue(WATERLOGGED, false);
        other = other.setValue(WATERLOGGED, false);
        Direction facing = state.getValue(FACING);
        if (facing.getOpposite() == other.getValue(FACING) && pDirection == facing) return true;
        if (other.getValue(FACING) != facing) return false;
        return pDirection.getAxis() != facing.getAxis();
    }

    @Override
    public boolean canFaceBeOccluded(BlockState state, Direction face) {
        return state.getValue(FACING)
                .getOpposite() == face;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
        if (pPlayer.isCrouching() && pPlayer.getItemInHand(pHand).equals(ItemStack.EMPTY)) {
            BlockState newState = pState;
            newState = newState.setValue(CONNECTS, !pState.getValue(CONNECTS));
            pLevel.setBlock(pPos, newState, Block.UPDATE_ALL);
            return InteractionResult.SUCCESS;
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    public boolean shouldFaceAlwaysRender(BlockState state, Direction face) {
        return true;
    }
}
