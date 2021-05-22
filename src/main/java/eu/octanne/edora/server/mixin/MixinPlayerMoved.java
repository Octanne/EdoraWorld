package eu.octanne.edora.server.mixin;

import eu.octanne.edora.EdoraMain;
import eu.octanne.edora.server.event.ServerEvents;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.injection.At;

@Mixin(Entity.class)
public class MixinPlayerMoved {

    Map<PlayerEntity,ChunkPos> lastPlayerPos = new HashMap<PlayerEntity,ChunkPos>();

    @Inject(at = @At(value = "NEW", target = "Lnet/minecraft/util/math/Vec3d;Vec3d"), method = "setPos")
    private void onMove(final double x, final double y, final double z, final CallbackInfo info) {
        if ( ((Entity) (Object) this).getType() != EntityType.PLAYER )
            return;
        //if it's a Player
        final PlayerEntity player = (PlayerEntity) (Object) this;
        ServerEvents.PLAYER_MOVED.invoker().playerMoved(player);

        final ChunkPos playerPos = new ChunkPos(player.getBlockPos());
        if(!( lastPlayerPos.containsKey(player) && lastPlayerPos.get(player).equals(playerPos) )){//skip if it's a player in the same chunk
            ChunkPos oldPos;
            if(!lastPlayerPos.containsKey(player)){
                oldPos = playerPos;
                lastPlayerPos.put(player, playerPos);
            }
            else{
                oldPos = lastPlayerPos.get(player);
                lastPlayerPos.replace(player, playerPos);
            }
            
            EdoraMain.LOGGER.log(Level.INFO,player.getName().asString() + " has moved to " + playerPos);
            ServerEvents.PLAYER_MOVED_CHUNK.invoker().playerMovedChunk(player, oldPos, playerPos);
        }
    }
}
