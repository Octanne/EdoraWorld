package eu.octanne.edora.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import eu.octanne.edora.client.EdoraClient;
import eu.octanne.edora.packet.PacketIdentifiers;
import eu.octanne.edora.packet.handler.MenuType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.KeyboardInput;

@Mixin(KeyboardInput.class)
public class MixinKeyboardInput {
    @Inject(at = @At("HEAD"), method = "tick", remap=true)
    private void onTick(CallbackInfo info) {
        if(EdoraClient.menuKeybind.wasPressed()){
            MinecraftClient client = EdoraClient.getClient();
            if(client.currentScreen == null) {
                // Ask to server if He can open and which menu open.
                // client.openScreen(new FactionChooseMenuScreen(client.player));
                PacketIdentifiers.sendClientAskOpenMenu(MenuType.PERSONAL_MENU);
            }
        }
    }
}