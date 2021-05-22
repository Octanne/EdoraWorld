package eu.octanne.edora.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;

@Mixin(InGameHud.class)
public class MixinInGameHud {

    @Inject(at = @At("RETURN"), method = "<init>(Lnet/minecraft/client/MinecraftClient;)V")
    private void constructClass(MinecraftClient client, CallbackInfo info){
        System.out.println("Game HUD defined !");
    }

}
