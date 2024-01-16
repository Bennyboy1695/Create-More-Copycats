package io.github.bennyboy1695.create_copycat.fabric;

import com.simibubi.create.foundation.data.CreateRegistrate;
import io.github.bennyboy1695.create_copycat.fabric.register.ModBlocks;
import io.github.bennyboy1695.create_copycat.fabric.register.ModCreativeTab;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateMoreCopycatsFabric implements ModInitializer {

    public static final String MOD_ID = "create_more_copycats";
    private static final Logger logger = LoggerFactory.getLogger("Create: More Copycats");
    private static final CreateRegistrate registrate = CreateRegistrate.create(MOD_ID);

    @Override
    public void onInitialize() {
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