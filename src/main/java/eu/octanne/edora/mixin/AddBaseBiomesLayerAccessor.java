package eu.octanne.edora.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.world.biome.layer.AddBaseBiomesLayer;

@Mixin(AddBaseBiomesLayer.class)
public interface AddBaseBiomesLayerAccessor {
  @Accessor("TEMPERATE_BIOMES")
  public static int[] getTemperateBiomes() {
    throw new AssertionError();
  }
 
  @Accessor("TEMPERATE_BIOMES")
  public static void setTemperateBiomes(int[] biomes) {
    throw new AssertionError();
  }

  @Accessor("COOL_BIOMES")
  public static int[] getCoolBiomes() {
    throw new AssertionError();
  }
 
  @Accessor("COOL_BIOMES")
  public static void setCoolBiomes(int[] biomes) {
    throw new AssertionError();
  }
}
