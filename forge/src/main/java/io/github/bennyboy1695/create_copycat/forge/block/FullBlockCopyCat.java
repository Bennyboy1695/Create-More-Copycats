package io.github.bennyboy1695.create_copycat.forge.block;

import com.simibubi.create.content.decoration.copycat.WaterloggedCopycatBlock;
import com.simibubi.create.foundation.placement.IPlacementHelper;
import com.simibubi.create.foundation.placement.PlacementHelpers;
import com.simibubi.create.foundation.placement.PlacementOffset;
import io.github.bennyboy1695.create_copycat.forge.register.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Predicate;

public class FullBlockCopyCat extends WaterloggedCopycatBlock {

    public static final BooleanProperty CONNECTS = CustomBlockProperties.CONNECTS;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static final int placementHelperId = PlacementHelpers.register(new FullBlockCopyCat.PlacementHelper());

    public FullBlockCopyCat(Properties pProperties) {
        super(pProperties);
        registerDefaultState(defaultBlockState().setValue(CONNECTS, true).setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder.add(CONNECTS).add(FACING));
    }

    @Override
    public boolean canConnectTexturesToward(BlockAndTintGetter reader, BlockPos fromPos, BlockPos toPos, BlockState state) {
        BlockState toState = reader.getBlockState(toPos);
        if (toState.hasProperty(CONNECTS) && !toState.getValue(CONNECTS)) return false;
        return state.getValue(CONNECTS);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer.isCrouching() && pPlayer.getItemInHand(pHand).equals(ItemStack.EMPTY)) {
            BlockState newState = pState;
            newState = newState.setValue(CONNECTS, !pState.getValue(CONNECTS));
            pLevel.setBlock(pPos, newState, Block.UPDATE_ALL);
            return InteractionResult.SUCCESS;
        }
        if (!pPlayer.isShiftKeyDown() && pPlayer.mayBuild()) {
            ItemStack heldItem = pPlayer.getItemInHand(pHand);
            IPlacementHelper placementHelper = PlacementHelpers.get(placementHelperId);
            if (placementHelper.matchesItem(heldItem)) {
                placementHelper.getOffset(pPlayer, pLevel, pState, pPos, pHit)
                        .placeInWorld(pLevel, (BlockItem) heldItem.getItem(), pPlayer, pHand, pHit);
                return InteractionResult.SUCCESS;
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    private static class PlacementHelper implements IPlacementHelper {

        @Override
        public @NotNull Predicate<ItemStack> getItemPredicate() {
            return ModBlocks.COPYCAT_BLOCK::isIn;
        }

        @Override
        public @NotNull Predicate<BlockState> getStatePredicate() {
            return ModBlocks.COPYCAT_BLOCK::has;
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
