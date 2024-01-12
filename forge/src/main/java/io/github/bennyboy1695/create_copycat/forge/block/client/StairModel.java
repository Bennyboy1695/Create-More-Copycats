package io.github.bennyboy1695.create_copycat.forge.block.client;

import com.simibubi.create.content.decoration.copycat.CopycatModel;
import com.simibubi.create.foundation.model.BakedModelHelper;
import com.simibubi.create.foundation.model.BakedQuadHelper;
import io.github.bennyboy1695.create_copycat.forge.CreateMoreCopycatsForge;
import io.github.bennyboy1695.create_copycat.forge.block.StairCopyCat;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.model.data.ModelData;

import java.util.ArrayList;
import java.util.List;

public class StairModel extends CopycatModel {

    public StairModel(BakedModel originalModel) {
        super(originalModel);
    }

    @Override
    protected List<BakedQuad> getCroppedQuads(BlockState state, Direction side, RandomSource rand, BlockState material, ModelData wrappedData, RenderType renderType) {
        Direction facing = state.getOptionalValue(StairCopyCat.FACING).orElse(Direction.SOUTH);
        StairsShape shape = state.getOptionalValue(StairCopyCat.STAIR_SHAPE).orElse(StairsShape.STRAIGHT);
        boolean upper = state.getOptionalValue(StairCopyCat.HALF).orElse(Half.BOTTOM) == Half.TOP;
        BakedModel model = getModelOf(material);
        List<BakedQuad> templateQuads = model.getQuads(material, side, rand, wrappedData, renderType);
        int size = templateQuads.size();
        if (size == 0) return new ArrayList<>();
        BakedQuad quad = templateQuads.get(0);
        List<BakedQuad> quads = new ArrayList<>();
        //TODO: Definitely a better way to do this, but i can't think of it currently XD
        switch (shape) {
            case STRAIGHT -> {
                switch (facing) {
                    case NORTH -> {
                        //bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 0, 2).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 2, 0, 2)), Vec3.ZERO)));
                        //back
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(Vec3.ZERO, 2, 2, 0), Vec3.ZERO)));
                        //top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 0, 1).move(1, 0, 0) : AABB.ofSize(Vec3.ZERO, 2, 0, 1).move(0, 1, 0)), Vec3.ZERO)));
                        //middle top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (AABB.ofSize(Vec3.ZERO, 2, 0, 1).move(0, .5, 1)), Vec3.ZERO)));
                        //left side bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 1) : AABB.ofSize(Vec3.ZERO, 0, 1, 2)), Vec3.ZERO)));
                        //left side top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 2).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(0, 1, 0)), Vec3.ZERO)));
                        //right side bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(1, 0, 0) : AABB.ofSize(Vec3.ZERO, 0, 1, 2).move(1, 0, 0)), Vec3.ZERO)));
                        //right side top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 2).move(1, 1, 0) : AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(1, 1, 0)), Vec3.ZERO)));
                        //front bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(0, 0, .5) : AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(0, 0, 1)), Vec3.ZERO)));
                        //front top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(0, 1, 1) : AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(0, 1, .5)), new Vec3(0, 0, 0))));
                    }
                    case SOUTH -> {
                        //bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 0, 2).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 2, 0, 2)), Vec3.ZERO)));
                        //back
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(Vec3.ZERO, 2, 2, 0).move(0, 0, 1), Vec3.ZERO)));
                        //top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 0, 1).move(0, 0, 1) : AABB.ofSize(Vec3.ZERO, 2, 0, 1).move(0, 1, 1)), Vec3.ZERO)));
                        //middle top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(Vec3.ZERO, 2, 0, 1).move(0, .5, 0), Vec3.ZERO)));
                        //left side bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(0, 0, 1) : AABB.ofSize(Vec3.ZERO, 0, 1, 2).move(0, 0, 1)), Vec3.ZERO)));
                        //left side top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 2).move(0, 1, 1) : AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(0, 1, 1)), Vec3.ZERO)));
                        //right side bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(1, 0, 1) : AABB.ofSize(Vec3.ZERO, 0, 1, 2).move(1, 0, 1)), Vec3.ZERO)));
                        //right side top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 2).move(1, 1, 1) : AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(1, 1, 1)), Vec3.ZERO)));
                        //front bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(0, 0, 0)), Vec3.ZERO)));
                        //front top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(0, 0, .5) : AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(0, 1, .5)), Vec3.ZERO)));
                    }
                    case EAST -> {
                        //bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 0, 2).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 2, 0, 2)), Vec3.ZERO)));
                        //back
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(Vec3.ZERO, 0, 2, 2).move(1, 0, 0), Vec3.ZERO)));
                        //top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 0, 2).move(1, 0, 0) : AABB.ofSize(Vec3.ZERO, 1, 0, 2).move(1, 1, 0)), Vec3.ZERO)));
                        //middle top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(Vec3.ZERO, 1, 0, 2).move(0, .5, 0), Vec3.ZERO)));
                        //left side bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(1, 0, 1) : AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(0, 0, 1)), Vec3.ZERO)));
                        //left side top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(1, 1, 1) : AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(1, 1, 1)), Vec3.ZERO)));
                        //right side bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(1, 0, 0) : AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(0, 0, 0)), Vec3.ZERO)));
                        //right side top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(1, 1, 0) : AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(1, 1, 0)), Vec3.ZERO)));
                        //front bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 2).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 0, 1, 2).move(0, 0, 0)), Vec3.ZERO)));
                        //front top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 2).move(.5, 0, 0) : AABB.ofSize(Vec3.ZERO, 0, 1, 2).move(.5, 1, 0)), Vec3.ZERO)));

                    }
                    case WEST -> {
                        //bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 0, 2).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 2, 0, 2)), Vec3.ZERO)));
                        //back
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(Vec3.ZERO, 0, 2, 2).move(0, 0, 0), Vec3.ZERO)));
                        //top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 0, 2).move(0, 0, 0) : AABB.ofSize(Vec3.ZERO, 1, 0, 2).move(0, 1, 1)), Vec3.ZERO)));
                        //middle top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(Vec3.ZERO, 1, 0, 2).move(1, .5, 0), Vec3.ZERO)));
                        //left side bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(0, 0, 1) : AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(1, 0, 1)), Vec3.ZERO)));
                        //left side top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(0, 1, 1) : AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(0, 1, 1)), Vec3.ZERO)));
                        //right side bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(0, 0, 0) : AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(1, 0, 0)), Vec3.ZERO)));
                        //right side top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(0, 1, 0)), Vec3.ZERO)));
                        //front bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 2).move(.5, 0, 0) : AABB.ofSize(Vec3.ZERO, 0, 1, 2).move(1, 0, 0)), Vec3.ZERO)));
                        //front top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 2).move(1, 1, 1) : AABB.ofSize(Vec3.ZERO, 0, 1, 2).move(.5, 1, 1)), Vec3.ZERO)));
                    }
                }
            }
            case INNER_LEFT -> {
                switch (facing) {
                    case NORTH -> {
                        //bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 0, 2).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 2, 0, 2)), Vec3.ZERO)));
                        //back right
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(Vec3.ZERO, 2, 2, 0), Vec3.ZERO)));
                        //back left
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(Vec3.ZERO, 0, 2, 2), Vec3.ZERO)));
                        //top right
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 0, 1).move(1, 0, 0) : AABB.ofSize(Vec3.ZERO, 2, 0, 1).move(0, 1, 0)), Vec3.ZERO)));
                        //top left
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 0, 1).move(0, 0, 1) : AABB.ofSize(Vec3.ZERO, 1, 0, 1).move(0, 1, 1)), Vec3.ZERO)));
                        //middle top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (AABB.ofSize(Vec3.ZERO, 1, 0, 1).move(1, .5, 1)), Vec3.ZERO)));
                        //right side bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(1, 0, 0) : AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(1, 0, 1)), Vec3.ZERO)));
                        //right side top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 2).move(1, 1, 0) : AABB.ofSize(Vec3.ZERO, 0, 2, 1).move(1, 1, 0)), Vec3.ZERO)));
                        //left side bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(0, 0, 1) : AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(0, 0, 1)), Vec3.ZERO)));
                        //left side top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(0, 1, 1) : AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(0, 1, 1)), Vec3.ZERO)));
                        //middle left
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(.5, 0, 1) : AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(.5, 1, 1)), Vec3.ZERO)));
                        //middle right
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(1, 0, .5) : AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(1, 1, .5)), Vec3.ZERO)));
                    }
                    case SOUTH -> {
                        //bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 0, 2).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 2, 0, 2)), Vec3.ZERO)));
                        //back right
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(Vec3.ZERO, 2, 2, 0).move(0, 0, 1), Vec3.ZERO)));
                        //back left
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(Vec3.ZERO, 0, 2, 2).move(1, 0, 0), Vec3.ZERO)));
                        //top right
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 0, 1).move(1, 0, 1) : AABB.ofSize(Vec3.ZERO, 2, 0, 1).move(0, 1, 1)), Vec3.ZERO)));
                        //top left
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 0, 1).move(1, 0, 0) : AABB.ofSize(Vec3.ZERO, 1, 0, 1).move(1, 1, 0)), Vec3.ZERO)));
                        //middle top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (AABB.ofSize(Vec3.ZERO, 1, 0, 1).move(0, .5, 0)), Vec3.ZERO)));
                        //right side bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(0, 0, 1) : AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(0, 0, 0)), Vec3.ZERO)));
                        //right side top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 2).move(0, 1, 1) : AABB.ofSize(Vec3.ZERO, 0, 2, 1).move(0, 1, 1)), Vec3.ZERO)));
                        //left side bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(1, 0, 0) : AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(1, 0, 0)), Vec3.ZERO)));
                        //left side top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(1, 1, 0) : AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(1, 1, 0)), Vec3.ZERO)));
                        //middle left
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(.5, 0, 0) : AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(.5, 1, 0)), Vec3.ZERO)));
                        //middle right
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(0, 0, .5) : AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(0, 1, .5)), Vec3.ZERO)));
                    }
                    case EAST -> {
                        //bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 0, 2).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 2, 0, 2)), Vec3.ZERO)));
                        //back right
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(Vec3.ZERO, 2, 2, 0).move(1, 0, 0), Vec3.ZERO)));
                        //back left
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(Vec3.ZERO, 0, 2, 2).move(1, 0, 1), Vec3.ZERO)));
                        //top right
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 0, 2).move(1, 0, 1) : AABB.ofSize(Vec3.ZERO, 1, 0, 2).move(1, 1, 0)), Vec3.ZERO)));
                        //top left
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 0, 1).move(0, 0, 0) : AABB.ofSize(Vec3.ZERO, 1, 0, 1).move(0, 1, 0)), Vec3.ZERO)));
                        //middle top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (AABB.ofSize(Vec3.ZERO, 1, 0, 1).move(0, .5, 1)), Vec3.ZERO)));
                        //right side bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(1, 0, 1) : AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(0, 0, 1)), Vec3.ZERO)));
                        //right side top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(1, 1, 1) : AABB.ofSize(Vec3.ZERO, 0, 2, 1).move(0, 1, 0)), Vec3.ZERO)));
                        //left side bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(0, 0, 0) : AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(0, 0, 1)), Vec3.ZERO)));
                        //left side top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 2).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(1, 1, 1)), Vec3.ZERO)));
                        //middle left
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(0, 0, .5) : AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(0, 1, .5)), Vec3.ZERO)));
                        //middle right
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(.5, 0, 1) : AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(.5, 1, 1)), Vec3.ZERO)));
                    }
                    case WEST -> {
                        //bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 0, 2).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 2, 0, 2)), Vec3.ZERO)));
                        //back right
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(Vec3.ZERO, 0, 2, 2).move(0, 0, 1), Vec3.ZERO)));
                        //back left
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(Vec3.ZERO, 2, 2, 0).move(0, 0, 1), Vec3.ZERO)));
                        //top right
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 0, 1).move(1, 0, 1) : AABB.ofSize(Vec3.ZERO, 1, 0, 2).move(0, 1, 1)), Vec3.ZERO)));
                        //top left
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 0, 1).move(0, 0, 0) : AABB.ofSize(Vec3.ZERO, 1, 0, 1).move(1, 1, 1)), Vec3.ZERO)));
                        //middle top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (AABB.ofSize(Vec3.ZERO, 1, 0, 1).move(1, .5, 0)), Vec3.ZERO)));
                        //right side bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(1, 0, 1) : AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(1, 0, 0)), Vec3.ZERO)));
                        //right side top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 2).move(1, 1, 1) : AABB.ofSize(Vec3.ZERO, 1, 2, 0).move(0, 1, 0)), Vec3.ZERO)));
                        //left side bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(0, 0, 0) : AABB.ofSize(Vec3.ZERO, 0, 1, 2).move(1, 0, 1)), Vec3.ZERO)));
                        //left side top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(1, 1, 1)), Vec3.ZERO)));
                        //middle left
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(1, 0, .5) : AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(1, 1, .5)), Vec3.ZERO)));
                        //middle right
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(.5, 0, 0) : AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(.5, 1, 0)), Vec3.ZERO)));
                    }
                }
            }
            case INNER_RIGHT -> {
                switch (facing) {
                    case NORTH -> {
                        //bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 0, 2).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 2, 0, 2)), Vec3.ZERO)));
                        //back right
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(Vec3.ZERO, 0, 2, 2).move(1, 0, 0), Vec3.ZERO)));
                        //back left
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(Vec3.ZERO, 2, 2, 0), Vec3.ZERO)));
                        //top right
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 0, 2).move(1, 0, 0) : AABB.ofSize(Vec3.ZERO, 1, 0, 2).move(1, 1, 0)), Vec3.ZERO)));
                        //top left
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 0, 1).move(0, 0, 0) : AABB.ofSize(Vec3.ZERO, 1, 0, 1).move(0, 1, 0)), Vec3.ZERO)));
                        //middle top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (AABB.ofSize(Vec3.ZERO, 1, 0, 1).move(0, .5, 1)), Vec3.ZERO)));
                        //right side bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(1, 0, 1) : AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(0, 0, 1)), Vec3.ZERO)));
                        //right side top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(1, 1, 1) : AABB.ofSize(Vec3.ZERO, 1, 2, 0).move(1, 1, 1)), Vec3.ZERO)));
                        //left side bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(0, 1, 1) : AABB.ofSize(Vec3.ZERO, 0, 1, 2).move(0, 0, 1)), Vec3.ZERO)));
                        //left side top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 2, 1).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(0, 1, 0)), Vec3.ZERO)));
                        //middle left
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(0, 0, .5) : AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(0, 1, .5)), Vec3.ZERO)));
                        //middle right
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(.5, 0, 1) : AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(.5, 1, 1)), Vec3.ZERO)));
                    }
                    case SOUTH -> {
                        //bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 0, 2).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 2, 0, 2)), Vec3.ZERO)));
                        //back right
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(Vec3.ZERO, 0, 2, 2).move(0, 0, 1), Vec3.ZERO)));
                        //back left
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(Vec3.ZERO, 2, 2, 0).move(0, 0, 1), Vec3.ZERO)));
                        //top right
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 0, 2).move(0, 0, 0) : AABB.ofSize(Vec3.ZERO, 1, 0, 2).move(0, 1, 1)), Vec3.ZERO)));
                        //top left
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 0, 1).move(1, 0, 1) : AABB.ofSize(Vec3.ZERO, 1, 0, 1).move(1, 1, 1)), Vec3.ZERO)));
                        //middle top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (AABB.ofSize(Vec3.ZERO, 1, 0, 1).move(1, .5, 0)), Vec3.ZERO)));
                        //right side bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(0, 0, 0) : AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(1, 0, 0)), Vec3.ZERO)));
                        //right side top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 1, 2, 0).move(0, 1, 0)), Vec3.ZERO)));
                        //left side bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(1, 1, 0) : AABB.ofSize(Vec3.ZERO, 0, 1, 2).move(1, 0, 1)), Vec3.ZERO)));
                        //left side top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 2, 1).move(1, 1, 1) : AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(1, 1, 1)), Vec3.ZERO)));
                        //middle left
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(1, 0, .5) : AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(1, 1, .5)), Vec3.ZERO)));
                        //middle right
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(.5, 0, .5) : AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(.5, 1, 0)), Vec3.ZERO)));
                    }
                    case EAST -> {
                        //bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 0, 2).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 2, 0, 2)), Vec3.ZERO)));
                        //back right
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(Vec3.ZERO, 2, 2, 0).move(0, 0, 1), Vec3.ZERO)));
                        //back left
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(Vec3.ZERO, 0, 2, 2).move(1, 0, 1), Vec3.ZERO)));
                        //top right
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 0, 1).move(1, 0, 1) : AABB.ofSize(Vec3.ZERO, 2, 0, 1).move(0, 1, 1)), Vec3.ZERO)));
                        //top left
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 0, 1).move(1, 0, 0) : AABB.ofSize(Vec3.ZERO, 1, 0, 1).move(1, 1, 0)), Vec3.ZERO)));
                        //middle top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (AABB.ofSize(Vec3.ZERO, 1, 0, 1).move(0, .5, 0)), Vec3.ZERO)));
                        //right side bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(0, 0, 1) : AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(0, 0, 0)), Vec3.ZERO)));
                        //right side top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 2).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 0, 2, 1).move(0, 1, 1)), Vec3.ZERO)));
                       //left side bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(1, 0, 0) : AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(1, 0, 0)), Vec3.ZERO)));
                        //left side top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(1, 1, 0) : AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(1, 1, 0)), Vec3.ZERO)));
                         //middle left
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(.5, 0, 0) : AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(.5, 1, 0)), Vec3.ZERO)));
                        //middle right
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(.5, 0, .5) : AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(0, 1, .5)), Vec3.ZERO)));
                    }
                    case WEST -> {
                        //bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 0, 2).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 2, 0, 2)), Vec3.ZERO)));
                        //back right
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(Vec3.ZERO, 2, 2, 0).move(1, 0, 0), Vec3.ZERO)));
                        //back left
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(Vec3.ZERO, 0, 2, 2).move(0, 0, 1), Vec3.ZERO)));
                        //top right
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 0, 1).move(1, 0, 0) : AABB.ofSize(Vec3.ZERO, 2, 0, 1).move(0, 1, 0)), Vec3.ZERO)));
                        //top left
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 0, 1).move(0, 0, 1) : AABB.ofSize(Vec3.ZERO, 1, 0, 1).move(0, 1, 1)), Vec3.ZERO)));
                        //middle top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (AABB.ofSize(Vec3.ZERO, 1, 0, 1).move(1, .5, 1)), Vec3.ZERO)));
                        //right side bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(1, 0, 0) : AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(1, 0, 1)), Vec3.ZERO)));
                        //right side top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 2).move(1, 1, 0) : AABB.ofSize(Vec3.ZERO, 0, 2, 1).move(1, 1, 0)), Vec3.ZERO)));
                        //left side bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(0, 0, 1) : AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(1, 0, 1)), Vec3.ZERO)));
                        //left side top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 1, 0).move(0, 1, 1) : AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(0, 1, 1)), Vec3.ZERO)));
                        //middle left
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(1, 0, .5) : AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(.5, 1, 1)), Vec3.ZERO)));
                        //middle right
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 0, 1, 1).move(.5, 0, 1) : AABB.ofSize(Vec3.ZERO, 1, 1, 0).move(1, 1, .5)), Vec3.ZERO)));
                    }
                }
            }
            case OUTER_LEFT -> {
                switch (facing) {
                    case NORTH -> {
                        //bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 1, 2).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 2, 1, 2)), Vec3.ZERO)));
                        //top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 1) : AABB.ofSize(Vec3.ZERO, 1, 1, 1).move(0, 1, 0)), Vec3.ZERO)));
                    }
                    case SOUTH -> {
                        //bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 1, 2).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 2, 1, 2)), Vec3.ZERO)));
                        //top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 1).move(1, 0, 1) : AABB.ofSize(Vec3.ZERO, 1, 1, 1).move(1, 1, 1)), Vec3.ZERO)));
                    }
                    case EAST -> {
                        //bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 1, 2).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 2, 1, 2)), Vec3.ZERO)));
                        //top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 1).move(1, 0, 0) : AABB.ofSize(Vec3.ZERO, 1, 1, 1).move(1, 1, 0)), Vec3.ZERO)));
                    }
                    case WEST -> {
                        //bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 1, 2).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 2, 1, 2)), Vec3.ZERO)));
                        //top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 1).move(0, 0, 1) : AABB.ofSize(Vec3.ZERO, 1, 1, 1).move(0, 1, 1)), Vec3.ZERO)));
                    }
                }
            }
            case OUTER_RIGHT -> {
                switch (facing) {
                    case NORTH -> {
                        //bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 1, 2).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 2, 1, 2)), Vec3.ZERO)));
                        //top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 1).move(1, 0, 0) : AABB.ofSize(Vec3.ZERO, 1, 1, 1).move(1, 1, 0)), Vec3.ZERO)));
                    }
                    case SOUTH -> {
                        //bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 1, 2).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 2, 1, 2)), Vec3.ZERO)));
                        //top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 1).move(0, 0, 1) : AABB.ofSize(Vec3.ZERO, 1, 1, 1).move(0, 1, 1)), Vec3.ZERO)));
                    }
                    case EAST -> {
                        //bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 1, 2).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 2, 1, 2)), Vec3.ZERO)));
                        //top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 1).move(1, 0, 1) : AABB.ofSize(Vec3.ZERO, 1, 1, 1).move(1, 1, 1)), Vec3.ZERO)));
                    }
                    case WEST -> {
                        //bottom
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 2, 1, 2).move(0, 1, 0) : AABB.ofSize(Vec3.ZERO, 2, 1, 2)), Vec3.ZERO)));
                        //top
                        quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), (upper ? AABB.ofSize(Vec3.ZERO, 1, 1, 1).move(0, 0, 0) : AABB.ofSize(Vec3.ZERO, 1, 1, 1).move(0, 1, 0)), Vec3.ZERO)));
                    }
                }
            }
        }
        return quads;
    }

}
