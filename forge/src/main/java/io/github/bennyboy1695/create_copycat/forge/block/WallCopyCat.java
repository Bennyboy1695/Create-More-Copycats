package io.github.bennyboy1695.create_copycat.forge.block;

import com.google.common.collect.ImmutableMap;
import com.simibubi.create.content.decoration.copycat.WaterloggedCopycatBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Iterator;
import java.util.Map;

public class WallCopyCat extends WaterloggedCopycatBlock {

    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final EnumProperty<WallSide> EAST_WALL = BlockStateProperties.EAST_WALL;
    public static final EnumProperty<WallSide> NORTH_WALL = BlockStateProperties.NORTH_WALL;
    public static final EnumProperty<WallSide> SOUTH_WALL = BlockStateProperties.SOUTH_WALL;
    public static final EnumProperty<WallSide> WEST_WALL = BlockStateProperties.WEST_WALL;

    private final Map<BlockState, VoxelShape> shapeByIndex;
    private final Map<BlockState, VoxelShape> collisionShapeByIndex;

    public WallCopyCat(Properties pProperties) {
        super(pProperties);
        registerDefaultState(defaultBlockState().setValue(UP, true).setValue(EAST_WALL, WallSide.NONE).setValue(NORTH_WALL, WallSide.NONE).setValue(SOUTH_WALL, WallSide.NONE).setValue(WEST_WALL, WallSide.NONE));
        this.shapeByIndex = this.makeShapes(4.0F, 3.0F, 16.0F, 0.0F, 14.0F, 16.0F);
        this.collisionShapeByIndex = this.makeShapes(4.0F, 3.0F, 24.0F, 0.0F, 24.0F, 24.0F);
    }

    private static VoxelShape applyWallShape(VoxelShape arg, WallSide arg2, VoxelShape arg3, VoxelShape arg4) {
        if (arg2 == WallSide.TALL) {
            return Shapes.or(arg, arg4);
        } else {
            return arg2 == WallSide.LOW ? Shapes.or(arg, arg3) : arg;
        }
    }

    private Map<BlockState, VoxelShape> makeShapes(float f, float g, float h, float i, float j, float k) {
        float l = 8.0F - f;
        float m = 8.0F + f;
        float n = 8.0F - g;
        float o = 8.0F + g;
        VoxelShape voxelShape = Block.box(l, 0.0, l, m, h, m);
        VoxelShape voxelShape2 = Block.box(n, i, 0.0, o, j, o);
        VoxelShape voxelShape3 = Block.box(n, i, n, o, j, 16.0);
        VoxelShape voxelShape4 = Block.box(0.0, i, n, o, j, o);
        VoxelShape voxelShape5 = Block.box(n, i, n, 16.0, j, o);
        VoxelShape voxelShape6 = Block.box(n, i, 0.0, o, k, o);
        VoxelShape voxelShape7 = Block.box(n, i, n, o, k, 16.0);
        VoxelShape voxelShape8 = Block.box(0.0, i, n, o, k, o);
        VoxelShape voxelShape9 = Block.box(n, i, n, 16.0, k, o);
        ImmutableMap.Builder<BlockState, VoxelShape> builder = ImmutableMap.builder();
        Iterator var21 = UP.getPossibleValues().iterator();

        while(var21.hasNext()) {
            Boolean boolean_ = (Boolean)var21.next();
            Iterator var23 = EAST_WALL.getPossibleValues().iterator();

            while(var23.hasNext()) {
                WallSide wallSide = (WallSide)var23.next();
                Iterator var25 = NORTH_WALL.getPossibleValues().iterator();

                while(var25.hasNext()) {
                    WallSide wallSide2 = (WallSide)var25.next();
                    Iterator var27 = WEST_WALL.getPossibleValues().iterator();

                    while(var27.hasNext()) {
                        WallSide wallSide3 = (WallSide)var27.next();
                        Iterator var29 = SOUTH_WALL.getPossibleValues().iterator();

                        while(var29.hasNext()) {
                            WallSide wallSide4 = (WallSide)var29.next();
                            VoxelShape voxelShape10 = Shapes.empty();
                            voxelShape10 = applyWallShape(voxelShape10, wallSide, voxelShape5, voxelShape9);
                            voxelShape10 = applyWallShape(voxelShape10, wallSide3, voxelShape4, voxelShape8);
                            voxelShape10 = applyWallShape(voxelShape10, wallSide2, voxelShape2, voxelShape6);
                            voxelShape10 = applyWallShape(voxelShape10, wallSide4, voxelShape3, voxelShape7);
                            if (boolean_) {
                                voxelShape10 = Shapes.or(voxelShape10, voxelShape);
                            }

                            BlockState blockState = this.defaultBlockState().setValue(UP, boolean_).setValue(EAST_WALL, wallSide).setValue(WEST_WALL, wallSide3).setValue(NORTH_WALL, wallSide2).setValue(SOUTH_WALL, wallSide4);
                            builder.put(blockState.setValue(WATERLOGGED, false), voxelShape10);
                            builder.put(blockState.setValue(WATERLOGGED, true), voxelShape10);
                        }
                    }
                }
            }
        }

        return builder.build();
    }

    public VoxelShape getShape(BlockState arg, BlockGetter arg2, BlockPos arg3, CollisionContext arg4) {
        return this.shapeByIndex.get(arg);
    }

    public VoxelShape getCollisionShape(BlockState arg, BlockGetter arg2, BlockPos arg3, CollisionContext arg4) {
        return this.collisionShapeByIndex.get(arg);
    }

    @Override
    public boolean canConnectTexturesToward(BlockAndTintGetter reader, BlockPos fromPos, BlockPos toPos, BlockState state) {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder.add(UP).add(EAST_WALL).add(NORTH_WALL).add(SOUTH_WALL).add(WEST_WALL));
    }
}
