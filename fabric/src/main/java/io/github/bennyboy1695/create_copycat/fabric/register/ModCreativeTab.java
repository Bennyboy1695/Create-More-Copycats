package io.github.bennyboy1695.create_copycat.fabric.register;

import io.github.bennyboy1695.create_copycat.fabric.CreateMoreCopycatsFabric;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTab extends CreativeModeTab {

    public ModCreativeTab(String label) {
        super(0, label);
    }

    public static void register() {
        CreateMoreCopycatsFabric.registrate().creativeModeTab(() -> new ModCreativeTab(CreateMoreCopycatsFabric.MOD_ID));
    }

    @Override
    public ItemStack makeIcon() {
        return ModBlocks.COPYCAT_SLAB.asStack();
    }
}
