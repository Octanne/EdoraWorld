package eu.octanne.edora.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BuiltinBiomes;

@Mixin(BuiltinBiomes.class)
public interface BuiltinBiomesAccessor {
    @Accessor("BY_RAW_ID")
    public static Int2ObjectMap<RegistryKey<Biome>> getRawIdMap() {
        throw new AssertionError();
    }
}
