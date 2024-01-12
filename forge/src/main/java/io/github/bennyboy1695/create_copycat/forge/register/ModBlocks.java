package io.github.bennyboy1695.create_copycat.forge.register;

import com.simibubi.create.content.decoration.copycat.CopycatPanelModel;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import io.github.bennyboy1695.create_copycat.forge.CreateMoreCopycatsForge;
import io.github.bennyboy1695.create_copycat.forge.block.FullBlockCopyCat;
import io.github.bennyboy1695.create_copycat.forge.block.SlabCopyCat;
import io.github.bennyboy1695.create_copycat.forge.block.StairCopyCat;
import io.github.bennyboy1695.create_copycat.forge.block.client.BlockModel;
import io.github.bennyboy1695.create_copycat.forge.block.client.SlabModel;
import io.github.bennyboy1695.create_copycat.forge.block.client.StairModel;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;

public class ModBlocks {

    public static final BlockEntry<SlabCopyCat> COPYCAT_SLAB = CreateMoreCopycatsForge.registrate()
            .block("slab_copycat", SlabCopyCat::new)
            .transform(BuilderTransformers.copycat())
            .properties(BlockBehaviour.Properties::noOcclusion)
            .onRegister(CreateRegistrate.blockModel(() -> SlabModel::new))
            .item()
            .transform(customItemModel("copycat", "slab"))
            .lang("Copycat Slab")
            .register();

    public static final BlockEntry<FullBlockCopyCat> COPYCAT_BLOCK = CreateMoreCopycatsForge.registrate()
            .block("block_copycat", FullBlockCopyCat::new)
            .transform(BuilderTransformers.copycat())
            .properties(BlockBehaviour.Properties::noOcclusion)
            .onRegister(CreateRegistrate.blockModel(() -> BlockModel::new))
            .item()
            .transform(customItemModel("copycat", "full_block"))
            .lang("Copycat Block")
            .register();

    public static final BlockEntry<StairCopyCat> COPYCAT_STAIR = CreateMoreCopycatsForge.registrate()
            .block("stair_copycat", StairCopyCat::new)
            .transform(BuilderTransformers.copycat())
            .properties(BlockBehaviour.Properties::noOcclusion)
            .onRegister(CreateRegistrate.blockModel(() -> StairModel::new))
            .item()
            .transform(customItemModel("copycat", "stair"))
            .lang("Copycat Stair")
            .register();

    public static void init() {}
}
