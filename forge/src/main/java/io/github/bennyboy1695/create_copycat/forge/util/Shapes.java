package io.github.bennyboy1695.create_copycat.forge.util;

import com.simibubi.create.foundation.utility.VoxelShaper;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.BiFunction;
import java.util.stream.IntStream;

public class Shapes {

    public static final VoxelShaper SLAB = extraComplicated(0, 0, 0, 16, 8 , 16).forDirectional(Direction.UP);
    public static VoxelShape TOP_SLAB_AABB = Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0);
    public static VoxelShape BOTTOM_SLAB_AABB = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
    public static VoxelShape OCTET_NNN = Block.box(0.0, 0.0, 0.0, 8.0, 8.0, 8.0);
    public static VoxelShape OCTET_NNP = Block.box(0.0, 0.0, 8.0, 8.0, 8.0, 16.0);
    public static VoxelShape OCTET_NPN = Block.box(0.0, 8.0, 0.0, 8.0, 16.0, 8.0);
    public static VoxelShape OCTET_NPP = Block.box(0.0, 8.0, 8.0, 8.0, 16.0, 16.0);
    public static VoxelShape OCTET_PNN = Block.box(8.0, 0.0, 0.0, 16.0, 8.0, 8.0);
    public static VoxelShape OCTET_PNP = Block.box(8.0, 0.0, 8.0, 16.0, 8.0, 16.0);
    public static VoxelShape OCTET_PPN = Block.box(8.0, 8.0, 0.0, 16.0, 16.0, 8.0);
    public static VoxelShape OCTET_PPP = Block.box(8.0, 8.0, 8.0, 16.0, 16.0, 16.0);
    public static VoxelShape[] TOP_SHAPES = makeShapes(TOP_SLAB_AABB, OCTET_NNN, OCTET_PNN, OCTET_NNP, OCTET_PNP);
    public static VoxelShape[] BOTTOM_SHAPES = makeShapes(BOTTOM_SLAB_AABB, OCTET_NPN, OCTET_PPN, OCTET_NPP, OCTET_PPP);
    public static int[] SHAPE_BY_STATE = new int[]{12, 5, 3, 10, 14, 13, 7, 11, 13, 7, 11, 14, 8, 4, 1, 2, 4, 1, 2, 8};

    public static VoxelShape cuboid(double x, double y, double z, double x2, double y2, double z2) {
        return Block.box(x, y, z, x2, y2, z2);
    }

    private static VoxelShape[] makeShapes(VoxelShape arg, VoxelShape arg2, VoxelShape arg3, VoxelShape arg4, VoxelShape arg5) {
        return IntStream.range(0, 16).mapToObj((i) -> makeStairShape(i, arg, arg2, arg3, arg4, arg5)).toArray(VoxelShape[]::new);
    }

    private static VoxelShape makeStairShape(int i, VoxelShape arg, VoxelShape arg2, VoxelShape arg3, VoxelShape arg4, VoxelShape arg5) {
        VoxelShape voxelshape = arg;
        if ((i & 1) != 0) {
            voxelshape = net.minecraft.world.phys.shapes.Shapes.or(arg, arg2);
        }
        if ((i & 2) != 0) {
            voxelshape = net.minecraft.world.phys.shapes.Shapes.or(voxelshape, arg3);
        }
        if ((i & 4) != 0) {
            voxelshape = net.minecraft.world.phys.shapes.Shapes.or(voxelshape, arg4);
        }
        if ((i & 8) != 0) {
            voxelshape = net.minecraft.world.phys.shapes.Shapes.or(voxelshape, arg5);
        }
        return voxelshape;
    }

    public static ExtraComplicated extraComplicated(double x, double y, double z, double x2, double y2, double z2) {
        return extraComplicated(cuboid(x, y, z, x2, y2, z2));
    }

    public static ExtraComplicated extraComplicated(VoxelShape original) {
        return new ExtraComplicated(original);
    }

    public static class ExtraComplicated {

        private VoxelShape original;

        public ExtraComplicated(VoxelShape original) {
            this.original = original;
        }

        public VoxelShaper apply(BiFunction<VoxelShape, Direction, VoxelShaper> function, Direction direction) {
            return function.apply(original, direction);
        }
        public VoxelShaper forDirectional(Direction direction) {
            return apply(VoxelShaper::forDirectional, direction);
        }
    }
}
