package eu.octanne.edora.biome.features;

import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeatures;

public class EdoraDefaultBiomeFeatures {
    
    public static void addCommonOres(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_COAL);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_IRON);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_GOLD);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_REDSTONE);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_LAPIS);
    }

}
