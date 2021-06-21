package eu.octanne.edora.biome;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import eu.octanne.edora.EdoraMain;
import eu.octanne.edora.biome.features.EdoraConfiguredFeatures;
import eu.octanne.edora.biome.features.EdoraDefaultBiomeFeatures;
import eu.octanne.edora.biome.surfacebuilder.ConfiguredEdoraSurfaceBuilders;
import eu.octanne.edora.mixin.AddBaseBiomesLayerAccessor;
import eu.octanne.edora.mixin.BuiltinBiomesAccessor;
import eu.octanne.edora.mixin.VanillaLayeredBiomeSourceAccessor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class EdoraBiomes {
    // Biomes
    public static final RegistryKey<Biome> JUNGLE_KEY = RegistryKey.of(Registry.BIOME_KEY,
            new Identifier(EdoraMain.MOD_ID, "jungle"));
    public static final RegistryKey<Biome> DARK_KEY = RegistryKey.of(Registry.BIOME_KEY,
            new Identifier(EdoraMain.MOD_ID, "dark"));
    public static final RegistryKey<Biome> ARID_KEY = RegistryKey.of(Registry.BIOME_KEY,
            new Identifier(EdoraMain.MOD_ID, "arid"));
    public static final RegistryKey<Biome> LANTIA_KEY = RegistryKey.of(Registry.BIOME_KEY,
            new Identifier(EdoraMain.MOD_ID, "lantia"));

    private static Biome JUNGLE = createEdoraJungle();
    private static Biome DARK = createEdoraDark();
    private static Biome ARID = createEdoraArid();
    private static Biome LANTIA = createEdoraLantia();

    public static void registryBiomes() {
        registerBiome(JUNGLE, JUNGLE_KEY);
        registerBiome(DARK, DARK_KEY);
        registerBiome(ARID, ARID_KEY);
        registerBiome(LANTIA, LANTIA_KEY);
    }

    private static void registerBiome(Biome biome, RegistryKey<Biome> key) {
        Registry.register(BuiltinRegistries.BIOME, key.getValue(), biome);
        BuiltinBiomesAccessor.getRawIdMap().put(BuiltinRegistries.BIOME.getRawId(biome), key);
        // We have to copy existing List because it is immutable.
        List<RegistryKey<Biome>> biomes = new ArrayList<>(VanillaLayeredBiomeSourceAccessor.getBiomes());
        biomes.add(key);
        VanillaLayeredBiomeSourceAccessor.setBiomes(biomes);
        AddBaseBiomesLayerAccessor.setTemperateBiomes(ArrayUtils.add(AddBaseBiomesLayerAccessor.getTemperateBiomes(),
                BuiltinRegistries.BIOME.getRawId(biome)));
        AddBaseBiomesLayerAccessor.setCoolBiomes(
                ArrayUtils.add(AddBaseBiomesLayerAccessor.getCoolBiomes(), BuiltinRegistries.BIOME.getRawId(biome)));
    }

    private static Biome createEdoraJungle() {
        // We specify what entities spawn and what features generate in the biome.
        // Aside from some structures, trees, rocks, plants and
        // custom entities, these are mostly the same for each biome.
        // Vanilla configured features for biomes are defined in DefaultBiomeFeatures.
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        generationSettings.surfaceBuilder(ConfiguredEdoraSurfaceBuilders.JUNGLE);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);

        DefaultBiomeFeatures.addJungleTrees(generationSettings);
        DefaultBiomeFeatures.addJungleGrass(generationSettings);
        DefaultBiomeFeatures.addJungleVegetation(generationSettings);

        // COMMON ORE TO ALL BIOME ADD
        EdoraDefaultBiomeFeatures.addCommonOres(generationSettings);
        // ADD LIKE THIS FOR SPECIFIC ORE TO SPAWN ON THE BIOME
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, EdoraConfiguredFeatures.ORE_BAUXYTE);

        return (new Biome.Builder()).precipitation(Biome.Precipitation.RAIN).category(Biome.Category.JUNGLE)
                .depth(0.125F).scale(0.05F).temperature(0.8F).downfall(0.4F)
                .effects((new BiomeEffects.Builder()).waterColor(0x3f76e4).waterFogColor(0x050533).fogColor(0xc0d8ff)
                        .skyColor(0x77adff).grassColor(0x33AD1C).build())
                .spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    private static Biome createEdoraDark() {
        // We specify what entities spawn and what features generate in the biome.
        // Aside from some structures, trees, rocks, plants and
        // custom entities, these are mostly the same for each biome.
        // Vanilla configured features for biomes are defined in DefaultBiomeFeatures.
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        generationSettings.surfaceBuilder(ConfiguredEdoraSurfaceBuilders.DARK);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);

        DefaultBiomeFeatures.addBadlandsPlateauTrees(generationSettings);
        DefaultBiomeFeatures.addBadlandsGrass(generationSettings);
        DefaultBiomeFeatures.addExtraDefaultFlowers(generationSettings);
        DefaultBiomeFeatures.addBadlandsVegetation(generationSettings);

        // COMMON ORE TO ALL BIOME ADD
        EdoraDefaultBiomeFeatures.addCommonOres(generationSettings);
        // ADD LIKE THIS FOR SPECIFIC ORE TO SPAWN ON THE BIOME
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, EdoraConfiguredFeatures.ORE_BAUXYTE);

        return (new Biome.Builder()).precipitation(Biome.Precipitation.RAIN).category(Biome.Category.NONE).depth(0.125F)
                .scale(0.05F).temperature(0.8F).downfall(0.4F)
                .effects((new BiomeEffects.Builder()).waterColor(0x3f76e4).waterFogColor(0x050533).fogColor(0xc0d8ff)
                        .skyColor(0x77adff).grassColor(0x275F2F).build())
                .spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    private static Biome createEdoraArid() {
        // We specify what entities spawn and what features generate in the biome.
        // Aside from some structures, trees, rocks, plants and
        // custom entities, these are mostly the same for each biome.
        // Vanilla configured features for biomes are defined in DefaultBiomeFeatures.
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        generationSettings.surfaceBuilder(ConfiguredEdoraSurfaceBuilders.ARID);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);

        DefaultBiomeFeatures.addSavannaTrees(generationSettings);
        DefaultBiomeFeatures.addSavannaGrass(generationSettings);
        DefaultBiomeFeatures.addSavannaTallGrass(generationSettings);

        // COMMON ORE TO ALL BIOME ADD
        EdoraDefaultBiomeFeatures.addCommonOres(generationSettings);
        // ADD LIKE THIS FOR SPECIFIC ORE TO SPAWN ON THE BIOME
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, EdoraConfiguredFeatures.ORE_BAUXYTE);

        return (new Biome.Builder()).precipitation(Biome.Precipitation.RAIN).category(Biome.Category.NONE).depth(0.125F)
                .scale(0.05F).temperature(0.8F).downfall(0.4F)
                .effects((new BiomeEffects.Builder()).waterColor(0x3f76e4).waterFogColor(0x050533).fogColor(0xc0d8ff)
                        .skyColor(0x77adff).grassColor(0xA9A900).build())
                .spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    private static Biome createEdoraLantia() {
        // We specify what entities spawn and what features generate in the biome.
        // Aside from some structures, trees, rocks, plants and
        // custom entities, these are mostly the same for each biome.
        // Vanilla configured features for biomes are defined in DefaultBiomeFeatures.
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        generationSettings.surfaceBuilder(ConfiguredEdoraSurfaceBuilders.LANTIA);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);

        DefaultBiomeFeatures.addJungleTrees(generationSettings);
        DefaultBiomeFeatures.addJungleGrass(generationSettings);
        DefaultBiomeFeatures.addJungleVegetation(generationSettings);

        // COMMON ORE TO ALL BIOME ADD
        EdoraDefaultBiomeFeatures.addCommonOres(generationSettings);
        // ADD LIKE THIS FOR SPECIFIC ORE TO SPAWN ON THE BIOME
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, EdoraConfiguredFeatures.ORE_BAUXYTE);

        return (new Biome.Builder()).precipitation(Biome.Precipitation.RAIN).category(Biome.Category.NONE).depth(0.125F)
                .scale(0.05F).temperature(0.8F).downfall(0.4F)
                .effects((new BiomeEffects.Builder()).waterColor(0x3f76e4).waterFogColor(0x050533).fogColor(0xc0d8ff)
                        .skyColor(0x77adff).grassColor(0x756A3B).build())
                .spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }
}
