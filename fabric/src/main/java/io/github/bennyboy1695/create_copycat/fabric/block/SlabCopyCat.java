package io.github.bennyboy1695.create_copycat.fabric.block;

import com.simibubi.create.content.decoration.copycat.WaterloggedCopycatBlock;
import com.simibubi.create.foundation.placement.IPlacementHelper;
import com.simibubi.create.foundation.placement.PlacementHelpers;
import com.simibubi.create.foundation.placement.PlacementOffset;
import io.github.bennyboy1695.create_copycat.fabric.register.ModBlocks;
import io.github.bennyboy1695.create_copycat.fabric.util.Shapes;
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
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Predicate;

public class SlabCopyCat extends WaterloggedCopycatBlock {

    public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final BooleanProperty CONNECTS = CustomBlockProperties.CONNECTS;
    private static final int placementHelperId = PlacementHelpers.register(new PlacementHelper());

    public SlabCopyCat(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.UP).setValue(CONNECTS, true));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState stateForPlacement = super.getStateForPlacement(pContext);
        return stateForPlacement.setValue(FACING, pContext.getNearestLookingDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder.add(FACING).add(CONNECTS));
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    public boolean canConnectTexturesToward(BlockAndTintGetter reader, BlockPos fromPos, BlockPos toPos, BlockState state) {
        if (!state.getValue(CONNECTS)) return false;
        Direction facing = state.getValue(FACING);
        BlockState toState = reader.getBlockState(toPos);
        if (toState.hasProperty(CONNECTS) && !toState.getValue(CONNECTS)) return false;
        if (toPos.equals(fromPos.relative(facing))) return false;
        BlockPos diff = fromPos.subtract(toPos);
        int coord = facing.getAxis().choose(diff.getX(), diff.getY(), diff.getZ());
        if (!toState.is(this)) return coord != -facing.getAxisDirection().getStep();
        if (isOccluded(state, toState, facing)) return true;
        return toState.setValue(WATERLOGGED, false) == state.setValue(WATERLOGGED, false) && coord == 0;
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
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        return Shapes.SLAB.get(state.getValue(FACING));
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



    private static class PlacementHelper implements IPlacementHelper {

        @Override
        public @NotNull Predicate<ItemStack> getItemPredicate() {
            return ModBlocks.COPYCAT_SLAB::isIn;
        }

        @Override
        public @NotNull Predicate<BlockState> getStatePredicate() {
            return ModBlocks.COPYCAT_SLAB::has;
        }

        @Override
        public @NotNull PlacementOffset getOffset(Player player, Level world, BlockState state, BlockPos pos, BlockHitResult ray) {
            List<Direction> dirs = IPlacementHelper.orderedByDistanceExceptAxis(pos,
                    ray.getLocation(),
                    state.getValue(FACING).getAxis(),
                    dir -> world.getBlockState(pos.relative(dir)).getMaterial().isReplaceable());
            if (dirs.isEmpty())
                return PlacementOffset.fail();
            else {
                return PlacementOffset.success(pos.relative(dirs.get(0)),
                        s -> s.setValue(FACING, state.getValue(FACING)));
            }
        }
    }
}
