package eu.octanne.edora.server.event;

import net.minecraft.network.ClientConnection;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerJoinEvent {

    private ClientConnection connection;
    private ServerPlayerEntity player;

    private boolean isFirstJoin;

    public PlayerJoinEvent(ClientConnection connection, ServerPlayerEntity player, boolean isFirstJoin) {
        this.connection = connection;
        this.player = player;
        this.isFirstJoin = isFirstJoin;
    }

    public boolean isFirstJoin() {
        return isFirstJoin;
    }

    public ClientConnection getConnection() {
        return connection;
    }

    public ServerPlayerEntity getPlayer() {
        return player;
    }

    public interface PlayerJoin {
        void onPlayerJoin(ClientConnection connection, ServerPlayerEntity player, boolean isFirstJoin);
    }
}
