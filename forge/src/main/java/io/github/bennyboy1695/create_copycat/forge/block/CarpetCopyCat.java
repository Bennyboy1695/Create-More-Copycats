package io.github.bennyboy1695.create_copycat.forge.block;

import com.simibubi.create.content.decoration.copycat.WaterloggedCopycatBlock;
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
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Predicate;

public class CarpetCopyCat extends WaterloggedCopycatBlock {

    public static final BooleanProperty CONNECTS = CustomBlockProperties.CONNECTS;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public CarpetCopyCat(Properties pProperties) {
        super(pProperties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder.add(CONNECTS).add(FACING));
    }

    @Override
    public boolean canConnectTexturesToward(BlockAndTintGetter reader, BlockPos fromPos, BlockPos toPos, BlockState state) {
        if (!state.getValue(CONNECTS)) return false;
        BlockState toState = reader.getBlockState(toPos);
        return toState.hasProperty(CONNECTS) && toState.getValue(CONNECTS);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        return Shapes.CARPET.get(Direction.UP);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer.isCrouching() && pPlayer.getItemInHand(pHand).equals(ItemStack.EMPTY)) {
            BlockState newState = pState;
            newState = newState.setValue(CONNECTS, !pState.getValue(CONNECTS));
            pLevel.setBlock(pPos, newState, Block.UPDATE_ALL);
            return InteractionResult.SUCCESS;
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
}
