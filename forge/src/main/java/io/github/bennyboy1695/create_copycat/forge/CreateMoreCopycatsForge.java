package io.github.bennyboy1695.create_copycat.forge;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.item.TooltipModifier;
import io.github.bennyboy1695.create_copycat.forge.datagen.MoreCopycatsDataGen;
import io.github.bennyboy1695.create_copycat.forge.register.ModBlocks;
import io.github.bennyboy1695.create_copycat.forge.register.ModCreativeTab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(CreateMoreCopycatsForge.MOD_ID)
public class CreateMoreCopycatsForge {

    public static final String MOD_ID = "create_more_copycats";
    private static final Logger logger = LoggerFactory.getLogger("Create: More Copycats");
    private static final CreateRegistrate registrate = CreateRegistrate.create(MOD_ID);

    static {
        registrate().setTooltipModifierFactory(item -> {
           return new ItemDescription.Modifier(item, TooltipHelper.Palette.STANDARD_CREATE)
                    .andThen(TooltipModifier.mapNull(KineticStats.create(item)));
        });
    }

    public CreateMoreCopycatsForge() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        registrate .registerEventListeners(modBus);
        modBus.addListener(EventPriority.LOWEST, MoreCopycatsDataGen::gatherData);

        ModCreativeTab.register();
        ModBlocks.init();
    }

    public static CreateRegistrate registrate() {
        return registrate;
    }

    public static Logger logger() {
        return logger;
    }

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}