package eu.octanne.edora.biome.surfacebuilder;

import eu.octanne.edora.EdoraMain;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class EdoraSurfaceBuilder {
    
    public static final SurfaceBuilder<TernarySurfaceConfig> JUNGLE;

    static {
        JUNGLE = registerSurfaceBuilder(new Identifier(EdoraMain.MOD_ID, "jungle"), new JungleSurfaceBuilder(TernarySurfaceConfig.CODEC));
    }
    
    private static <C extends SurfaceConfig, F extends SurfaceBuilder<C>> F registerSurfaceBuilder(Identifier id, F surfaceBuilder) {
        return Registry.register(Registry.SURFACE_BUILDER, id, surfaceBuilder);
    }

}
