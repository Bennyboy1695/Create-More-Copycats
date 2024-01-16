package io.github.bennyboy1695.create_copycat.fabric.mixin_bridge;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public interface CopycatModelAccess {

    List<BakedQuad> more_copycats$getCroppedQuads(BlockState state, Direction side, RandomSource rand,
                                                  BlockState material, RenderType renderType);
}
