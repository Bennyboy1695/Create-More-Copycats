package io.github.bennyboy1695.create_copycat.forge.block.client;

import com.simibubi.create.content.decoration.copycat.CopycatModel;
import com.simibubi.create.foundation.model.BakedModelHelper;
import com.simibubi.create.foundation.model.BakedQuadHelper;
import io.github.bennyboy1695.create_copycat.forge.block.LayerCopyCat;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.model.data.ModelData;

import java.util.ArrayList;
import java.util.List;

public class LayerModel extends CopycatModel {

    protected static final AABB CUBE = new AABB(BlockPos.ZERO);
    private static final double[] SIZE_BY_LAYER = new double[]{0.0, 0.25, 0.5, 0.75, 1.0, 1.25, 1.5, 1.75, 2.0};

    public LayerModel(BakedModel originalModel) {
        super(originalModel);
    }

    @Override
    protected List<BakedQuad> getCroppedQuads(BlockState state, Direction side, RandomSource rand, BlockState material, ModelData wrappedData, RenderType renderType) {
        BakedModel originalModel = getModelOf(material);
        if (originalModel instanceof LayerModel impl)
            return impl.originalModel.getQuads(state, side, rand, wrappedData, renderType);
        BakedModel model = getModelOf(material);
        List<BakedQuad> templateQuads = model.getQuads(material, side, rand, wrappedData, renderType);
        List<BakedQuad> quads = new ArrayList<>();
        for (BakedQuad quad : templateQuads) {
            quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(Vec3.ZERO, 2, SIZE_BY_LAYER[state.getValue(LayerCopyCat.LAYERS)], 2), Vec3.ZERO)));
        }
        return quads;
    }
}
