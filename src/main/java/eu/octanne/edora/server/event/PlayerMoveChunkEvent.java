package eu.octanne.edora.server.event;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.ChunkPos;

public class PlayerMoveChunkEvent {
    private PlayerEntity player;
    private ChunkPos oldPos;
    private ChunkPos newPos;

    public PlayerMoveChunkEvent(PlayerEntity player, ChunkPos oldPos, ChunkPos newPos) {
        this.player = player;
        this.oldPos = oldPos;
        this.newPos = newPos;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public ChunkPos getOldPos() {
        return oldPos;
    }

    public ChunkPos getNewPos() {
        return newPos;
    }

    public interface PlayerMoveChunk {
        void onPlayerMoveChunk(PlayerEntity player, ChunkPos oldPos, ChunkPos newPos);
    }
}
