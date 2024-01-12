package io.github.bennyboy1695.create_copycat.forge.register;

import io.github.bennyboy1695.create_copycat.forge.CreateMoreCopycatsForge;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTab extends CreativeModeTab {

    public ModCreativeTab(String label) {
        super(label);
    }

    public static void register() {
        CreateMoreCopycatsForge.registrate().creativeModeTab(() -> new ModCreativeTab(CreateMoreCopycatsForge.MOD_ID));
    }

    @Override
    public ItemStack makeIcon() {
        return ModBlocks.COPYCAT_SLAB.asStack();
    }
}
