package eu.octanne.edora.client.mixin;

import java.util.Map;

import com.google.common.collect.Maps;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import eu.octanne.edora.materials.EdoraArmorMaterial;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Identifier;

@Mixin(ArmorFeatureRenderer.class)
public class MixinArmorFeatureRenderer {
    
    @Shadow
    private static final Map<String, Identifier> ARMOR_TEXTURE_CACHE = Maps.newHashMap();

    //@Inject(at = @At("HEAD"), method = "getArmorTexture(Lnet/minecraft/item/ArmorItem;ZLjava/lang/String;)Lnet/minecraft/util/Identifier;")
    @Overwrite
    private Identifier getArmorTexture(ArmorItem item, boolean legs, @Nullable String overlay) {
        if(item.getMaterial() instanceof EdoraArmorMaterial){
            String var10000 = item.getMaterial().getName();
            String string = "edora:textures/models/armor/" + var10000 + "_layer_" + 
                    (legs ? 2 : 1) + (overlay == null ? "" : "_" + overlay) + ".png";
            return (Identifier)ARMOR_TEXTURE_CACHE.computeIfAbsent(string, Identifier::new);
        }else{
            String var10000 = item.getMaterial().getName();
            String string = "textures/models/armor/" + var10000 + "_layer_" + (legs ? 2 : 1) + (overlay == null ? "" : "_" + overlay) + ".png";
            return (Identifier)ARMOR_TEXTURE_CACHE.computeIfAbsent(string, Identifier::new);
        }
    }
}
