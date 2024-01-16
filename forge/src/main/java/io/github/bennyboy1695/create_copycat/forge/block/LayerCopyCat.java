package io.github.bennyboy1695.create_copycat.forge.block;

import com.simibubi.create.content.decoration.copycat.WaterloggedCopycatBlock;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.foundation.placement.IPlacementHelper;
import com.simibubi.create.foundation.placement.PlacementHelpers;
import com.simibubi.create.foundation.placement.PlacementOffset;
import io.github.bennyboy1695.create_copycat.forge.register.ModBlocks;
import io.github.bennyboy1695.create_copycat.forge.util.Shapes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Predicate;

public class LayerCopyCat extends WaterloggedCopycatBlock {

    public static final IntegerProperty LAYERS = BlockStateProperties.LAYERS;
    public static final BooleanProperty CONNECTS = CustomBlockProperties.CONNECTS;

    private static final VoxelShape[] SHAPE_BY_LAYER = new VoxelShape[]{net.minecraft.world.phys.shapes.Shapes.empty(), Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)};

    public LayerCopyCat(Properties pProperties) {
        super(pProperties);
        registerDefaultState(defaultBlockState().setValue(LAYERS, 1).setValue(CONNECTS, true));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState stateForPlacement = pContext.getLevel().getBlockState(pContext.getClickedPos());
        if (stateForPlacement.is(this)) {
            int i = stateForPlacement.getValue(LAYERS);
            return stateForPlacement.setValue(LAYERS, Math.min(8, i + 1));
        } else {
            return super.getStateForPlacement(pContext);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder.add(LAYERS).add(CONNECTS));
    }

    @Override
    public boolean canConnectTexturesToward(BlockAndTintGetter reader, BlockPos fromPos, BlockPos toPos, BlockState state) {
        if (!state.getValue(CONNECTS)) return false;
        BlockState toState = reader.getBlockState(toPos);
        return toState.hasProperty(CONNECTS) && toState.getValue(CONNECTS);
    }

    public @NotNull VoxelShape getShape(BlockState arg, BlockGetter arg2, BlockPos arg3, CollisionContext arg4) {
        return SHAPE_BY_LAYER[arg.getValue(LAYERS)];
    }

    public @NotNull VoxelShape getCollisionShape(BlockState arg, BlockGetter arg2, BlockPos arg3, CollisionContext arg4) {
        return SHAPE_BY_LAYER[arg.getValue(LAYERS)];
    }

    public @NotNull VoxelShape getBlockSupportShape(BlockState arg, BlockGetter arg2, BlockPos arg3) {
        return SHAPE_BY_LAYER[arg.getValue(LAYERS)];
    }

    public @NotNull VoxelShape getVisualShape(BlockState arg, BlockGetter arg2, BlockPos arg3, CollisionContext arg4) {
        return SHAPE_BY_LAYER[arg.getValue(LAYERS)];
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer.getItemInHand(pHand).getItem().equals(ModBlocks.COPYCAT_LAYER.asStack().getItem())) {
            if (pState.getValue(LAYERS) < 8) {
                BlockState newState = pState;
                newState = newState.setValue(LAYERS, pState.getValue(LAYERS) + 1);
                pLevel.setBlock(pPos, newState, Block.UPDATE_ALL);
                if (!pPlayer.isCreative())
                    pPlayer.getItemInHand(pHand).shrink(1);
                return InteractionResult.SUCCESS;
            }
        }
        if (pPlayer.isCrouching() && pPlayer.getItemInHand(pHand).equals(ItemStack.EMPTY)) {
            BlockState newState = pState;
            newState = newState.setValue(CONNECTS, !pState.getValue(CONNECTS));
            pLevel.setBlock(pPos, newState, Block.UPDATE_ALL);
            return InteractionResult.SUCCESS;
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    public InteractionResult onSneakWrenched(BlockState state, UseOnContext context) {
        if (state.getValue(LAYERS) > 1) {
            BlockState newState = state;
            newState = newState.setValue(LAYERS, state.getValue(LAYERS) - 1);
            context.getLevel().setBlock(context.getClickedPos(), newState, Block.UPDATE_ALL);
            context.getPlayer().getInventory().placeItemBackInInventory(ModBlocks.COPYCAT_LAYER.asStack());
            return InteractionResult.SUCCESS;
        }
        return super.onSneakWrenched(state, context);
    }

}
