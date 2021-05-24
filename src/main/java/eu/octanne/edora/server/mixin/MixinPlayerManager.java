package eu.octanne.edora.server.mixin;

import com.mojang.authlib.GameProfile;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import eu.octanne.edora.server.event.ServerEvents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.UserCache;

@Mixin(PlayerManager.class)
public class MixinPlayerManager {

    @Shadow MinecraftServer server;

    @Inject(at = @At(shift= Shift.AFTER, value= "INVOKE_ASSIGN", target= "Lnet/minecraft/server/PlayerManager;loadPlayerData(Lnet/minecraft/server/network/ServerPlayerEntity;)Lnet/minecraft/nbt/CompoundTag;"), 
        method = "onPlayerConnect(Lnet/minecraft/network/ClientConnection;Lnet/minecraft/server/network/ServerPlayerEntity;)V",
        locals = LocalCapture.CAPTURE_FAILSOFT)
    private void onPlayerJoin(ClientConnection clientConnection, ServerPlayerEntity entity, CallbackInfo info,
    GameProfile gameProfile, UserCache userCache, String string, CompoundTag compoundTag){
        ServerEvents.PLAYER_JOIN.invoker().onPlayerJoin(clientConnection, entity, compoundTag == null);
    }
    
}