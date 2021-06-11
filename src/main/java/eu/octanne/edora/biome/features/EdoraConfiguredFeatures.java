package eu.octanne.edora.biome.features;

import com.google.common.collect.ImmutableList;

import eu.octanne.edora.block.EdoraBlocks;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class EdoraConfiguredFeatures {
    
    /* ORE FEATURES */
    public static final ConfiguredFeature<?, ?> ORE_BAUXYTE;
    private static final ImmutableList<OreFeatureConfig.Target> ORE_BAUXYTE_TARGET;

    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<FC, ?> configuredFeature) {
        return (ConfiguredFeature)Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, (String)id, configuredFeature);
    }

    static {
        ORE_BAUXYTE_TARGET = ImmutableList.of(OreFeatureConfig.createTarget(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, EdoraBlocks.ORE_BAUXYTE.getDefaultState()), OreFeatureConfig.createTarget(OreFeatureConfig.Rules.DEEPSLATE_ORE_REPLACEABLES, EdoraBlocks.DEEPSLATE_ORE_BAUXYTE.getDefaultState()));
        ORE_BAUXYTE = register("ore_bauxyte", (ConfiguredFeature)((ConfiguredFeature)((ConfiguredFeature)Feature.ORE.configure(new OreFeatureConfig(ORE_BAUXYTE_TARGET, 9)).uniformRange(YOffset.getBottom(), YOffset.fixed(31))).spreadHorizontally()).repeat(4));
    }
   
}
