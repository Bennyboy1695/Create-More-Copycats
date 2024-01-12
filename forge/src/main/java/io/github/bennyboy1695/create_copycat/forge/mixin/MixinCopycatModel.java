package io.github.bennyboy1695.create_copycat.forge.mixin;

import com.simibubi.create.content.decoration.copycat.CopycatModel;
import io.github.bennyboy1695.create_copycat.forge.mixin_bridge.CopycatModelAccess;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(value = CopycatModel.class, remap = false)
public abstract class MixinCopycatModel implements CopycatModelAccess {

    @Shadow
    protected abstract List<BakedQuad> getCroppedQuads(BlockState state, Direction side, RandomSource rand, BlockState material, ModelData wrappedData, RenderType renderType);
    @Override
    public List<BakedQuad> more_copycats$getCroppedQuads(BlockState state, Direction side, RandomSource rand, BlockState material, ModelData wrappedData, RenderType renderType) {
        return getCroppedQuads(state, side, rand, material, wrappedData, renderType);
    }
}
