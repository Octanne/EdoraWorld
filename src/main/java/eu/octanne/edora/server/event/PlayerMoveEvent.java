package eu.octanne.edora.server.event;

import net.minecraft.entity.player.PlayerEntity;

public class PlayerMoveEvent {
    
    private PlayerEntity player;

    public PlayerMoveEvent(PlayerEntity player) {
        this.player = player;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public interface PlayerMove {
        void onPlayerMove(PlayerEntity player);
    }
}
