package eu.octanne.edora.server.event;

import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerLeaveEvent {

    private ServerPlayerEntity player;

    public PlayerLeaveEvent(ServerPlayerEntity player) {
        this.player = player;
    }

    public ServerPlayerEntity getPlayer() {
        return player;
    }

    public interface PlayerLeave {
        void onPlayerLeave(ServerPlayerEntity player);
    }
}
