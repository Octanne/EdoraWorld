package eu.octanne.edora.biome;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import eu.octanne.edora.EdoraMain;
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

public class EdoraBiomes {
    // Biomes
    public static final RegistryKey<Biome> JUNGLE_KEY = RegistryKey.of(Registry.BIOME_KEY, 
        new Identifier(EdoraMain.MOD_ID, "jungle"));
    private static Biome JUNGLE = createEdoraJungle();

    @SuppressWarnings("deprecated")
    public static void registryBiomes() {
        registerBiome(JUNGLE,JUNGLE_KEY);
    }

    private static void registerBiome(Biome biome, RegistryKey<Biome> key) {
        Registry.register(BuiltinRegistries.BIOME, key.getValue(), biome);
        BuiltinBiomesAccessor.getRawIdMap().put(BuiltinRegistries.BIOME.getRawId(biome), key);
        // We have to copy existing List because it is immutable.
        List<RegistryKey<Biome>> biomes = new ArrayList<>(VanillaLayeredBiomeSourceAccessor.getBiomes());
        biomes.add(key);
        VanillaLayeredBiomeSourceAccessor.setBiomes(biomes);
        AddBaseBiomesLayerAccessor.setTemperateBiomes(
            ArrayUtils.add(AddBaseBiomesLayerAccessor.getTemperateBiomes(), 
            BuiltinRegistries.BIOME.getRawId(biome)));
        AddBaseBiomesLayerAccessor.setCoolBiomes(
            ArrayUtils.add(AddBaseBiomesLayerAccessor.getCoolBiomes(), 
            BuiltinRegistries.BIOME.getRawId(biome)));
    }

    private static Biome createEdoraJungle() {
        // We specify what entities spawn and what features generate in the biome.
        // Aside from some structures, trees, rocks, plants and
        //   custom entities, these are mostly the same for each biome.
        // Vanilla configured features for biomes are defined in DefaultBiomeFeatures.
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        generationSettings.surfaceBuilder(ConfiguredEdoraSurfaceBuilders.JUNGLE);
        return (new Biome.Builder())
        .precipitation(Biome.Precipitation.RAIN)
        .category(Biome.Category.JUNGLE)
        .depth(0.125F)
        .scale(0.05F)
        .temperature(0.8F)
        .downfall(0.4F)
        .effects((new BiomeEffects.Builder())
            .waterColor(0x3f76e4)
            .waterFogColor(0x050533)
            .fogColor(0xc0d8ff)
            .skyColor(0x77adff)
            .build())
        .spawnSettings(spawnSettings.build())
        .generationSettings(generationSettings.build())
        .build();
    }
}
