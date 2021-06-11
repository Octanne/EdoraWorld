package eu.octanne.edora.biome.surfacebuilder;

import eu.octanne.edora.EdoraMain;
import eu.octanne.edora.block.EdoraBlocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class ConfiguredEdoraSurfaceBuilders {
    // SurfaceBuilder defines how the surface of your biome looks.
    public static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> JUNGLE;
    
    static private <SC extends SurfaceConfig> ConfiguredSurfaceBuilder<SC> register(Identifier id, ConfiguredSurfaceBuilder<SC> configuredSurfaceBuilder) {
        return Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, id, configuredSurfaceBuilder);
    }

    static {
        JUNGLE = register(new Identifier(EdoraMain.MOD_ID,"jungle"), EdoraSurfaceBuilder.JUNGLE
            .withConfig(new TernarySurfaceConfig(EdoraBlocks.GRASS_BLOCK_JUNGLE.getDefaultState(),
                                                 EdoraBlocks.DIRT_JUNGLE.getDefaultState(),
                                                 EdoraBlocks.DIRT_JUNGLE.getDefaultState())));
    }



}
