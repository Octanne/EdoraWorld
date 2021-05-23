package eu.octanne.edora.server.mixin;

import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import eu.octanne.edora.EdoraMain;
import eu.octanne.edora.server.event.ServerEvents;

import net.minecraft.network.ClientConnection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(PlayerManager.class)
public class MixinPlayerManager {

    @Shadow MinecraftServer server;

    @Inject(at = @At("TAIL"), method = "onPlayerConnect(Lnet/minecraft/network/ClientConnection;Lnet/minecraft/server/network/ServerPlayerEntity;)V",
     locals = LocalCapture.PRINT)
    private void onPlayerJoin(ClientConnection clientConnection, ServerPlayerEntity entity, CallbackInfo info/*, CompoundTag compoundTag*/){
        ServerEvents.PLAYER_JOIN.invoker().onPlayerJoin(server, clientConnection, entity);
        /*if(compoundTag == null) {
            EdoraMain.log(Level.INFO, "The player is new no data to querries");
        }*/
    }
}