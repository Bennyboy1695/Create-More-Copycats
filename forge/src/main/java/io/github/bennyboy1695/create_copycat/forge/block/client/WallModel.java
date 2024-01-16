package io.github.bennyboy1695.create_copycat.forge.block.client;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.decoration.copycat.CopycatModel;
import com.simibubi.create.content.decoration.copycat.CopycatSpecialCases;
import com.simibubi.create.foundation.model.BakedModelHelper;
import com.simibubi.create.foundation.model.BakedQuadHelper;
import com.simibubi.create.foundation.utility.Iterate;
import io.github.bennyboy1695.create_copycat.forge.block.SlabCopyCat;
import io.github.bennyboy1695.create_copycat.forge.mixin_bridge.CopycatModelAccess;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.model.data.ModelData;

import java.util.ArrayList;
import java.util.List;

public class WallModel extends CopycatModel {

    protected static final AABB CUBE = new AABB(BlockPos.ZERO);

    public WallModel(BakedModel originalModel) {
        super(originalModel);
    }

    @Override
    protected List<BakedQuad> getCroppedQuads(BlockState state, Direction side, RandomSource rand, BlockState material, ModelData wrappedData, RenderType renderType) {
        BakedModel originalModel = getModelOf(material);
        if (originalModel instanceof WallModel impl)
            return impl.originalModel.getQuads(state, side, rand, wrappedData, renderType);
        BakedModel model = getModelOf(material);
        List<BakedQuad> templateQuads = model.getQuads(material, side, rand, wrappedData, renderType);
        List<BakedQuad> quads = new ArrayList<>();
        for (BakedQuad quad : templateQuads) {
            quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), AABB.ofSize(new Vec3(0.5, 0, 0.5), 0.5, 2, .5), Vec3.ZERO)));
        }
        return quads;
    }
}
