package io.github.bennyboy1695.create_copycat.forge.datagen;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.utility.FilesHelper;
import com.tterrag.registrate.providers.ProviderType;
import io.github.bennyboy1695.create_copycat.forge.CreateMoreCopycatsForge;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.Map;
import java.util.function.BiConsumer;

public class MoreCopycatsDataGen {
        private static final CreateRegistrate REGISTRATE = CreateMoreCopycatsForge.registrate();

        public static void gatherData(GatherDataEvent event) {
            addExtraRegistrateData();

            DataGenerator generator = event.getGenerator();
            ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        }

        private static void addExtraRegistrateData() {
            REGISTRATE.addDataGenerator(ProviderType.LANG, provider -> {
                BiConsumer<String, String> langConsumer = provider::add;
                provideDefaultLang("tooltips", langConsumer);
            });
        }

        private static void provideDefaultLang(String fileName, BiConsumer<String, String> consumer) {
            String path = "assets/create_more_copycats/lang/default/" + fileName + ".json";
            JsonElement jsonElement = FilesHelper.loadJsonResource(path);
            if (jsonElement == null) {
                throw new IllegalStateException(String.format("Could not find default lang file: %s", path));
            }
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().getAsString();
                consumer.accept(key, value);
            }
        }
}
