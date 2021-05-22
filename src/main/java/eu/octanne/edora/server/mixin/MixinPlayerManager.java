package eu.octanne.edora.server.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import eu.octanne.edora.server.event.ServerEvents;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(PlayerManager.class)
public class MixinPlayerManager {

    @Shadow MinecraftServer server;

    @Inject(at = @At("RETURN"), method = "onPlayerConnect(Lnet/minecraft/network/ClientConnection;Lnet/minecraft/server/network/ServerPlayerEntity;)V")
    private void onPlayerJoin(ClientConnection clientConnection, ServerPlayerEntity entity, CallbackInfo info){
        ServerEvents.PLAYER_JOIN.invoker().onPlayerJoin(server, clientConnection, entity);
    }
}