package eu.octanne.edora.server.mixin;

import java.util.HashMap;
import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import eu.octanne.edora.server.event.ServerEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.ChunkPos;

@Mixin(Entity.class)
public class MixinPlayerMoved {

    Map<PlayerEntity,ChunkPos> lastPlayerPos = new HashMap<PlayerEntity,ChunkPos>();

    @Inject(at = @At(value = "NEW", target = "Lnet/minecraft/util/math/Vec3d;Vec3d"), method = "setPos")
    private void onMove(final double x, final double y, final double z, final CallbackInfo info) {
        if ( ((Entity) (Object) this).getType() != EntityType.PLAYER )
            return;
        //if it's a Player
        final PlayerEntity player = (PlayerEntity) (Object) this;
        ServerEvents.PLAYER_MOVED.invoker().onPlayerMove(player);

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
            ServerEvents.PLAYER_MOVED_CHUNK.invoker().onPlayerMoveChunk(player, oldPos, playerPos);
        }
    }
}
