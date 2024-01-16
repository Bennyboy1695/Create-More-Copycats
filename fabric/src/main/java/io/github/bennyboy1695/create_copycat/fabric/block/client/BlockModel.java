package io.github.bennyboy1695.create_copycat.fabric.block.client;

import com.simibubi.create.content.decoration.copycat.CopycatModel;
import com.simibubi.create.foundation.model.BakedModelHelper;
import com.simibubi.create.foundation.model.BakedQuadHelper;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BlockModel extends CopycatModel {

    protected static final AABB CUBE = new AABB(BlockPos.ZERO);

    public BlockModel(BakedModel originalModel) {
        super(originalModel);
    }

    @Override
    protected void emitBlockQuadsInner(BlockAndTintGetter blockView, BlockState state, BlockPos pos, Supplier<RandomSource> randomSupplier, RenderContext context, BlockState material, CullFaceRemovalData cullFaceRemovalData, OcclusionData occlusionData) {

    }

    @Override
    protected List<BakedQuad> getQuads(BlockState state, Direction side, RandomSource rand, BlockState material, RenderType renderType) {
        BakedModel originalModel = getModelOf(material);
        if (originalModel instanceof BlockModel impl)
            return impl.getQuads(state, side, rand, renderType);
        BakedModel model = getModelOf(material);
        List<BakedQuad> templateQuads = model.getQuads(material, side, rand, wrappedData, renderType);
        List<BakedQuad> quads = new ArrayList<>();
        for (BakedQuad quad : templateQuads) {
            quads.add(BakedQuadHelper.cloneWithCustomGeometry(quad, BakedModelHelper.cropAndMove(quad.getVertices(), quad.getSprite(), CUBE, Vec3.ZERO)));
        }
        return quads;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState blockState, @Nullable Direction direction, RandomSource randomSource) {
        return null;
    }
}
