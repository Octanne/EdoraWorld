package eu.octanne.edora.server.event;

import net.minecraft.network.ClientConnection;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

public class PlayerJoinEvent {

    private ClientConnection connection;
    private ServerPlayerEntity player;

    private boolean isFirstJoin;

    private MutableText joinMessage = null;

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

    public void setJoinMessage(MutableText text) {
        joinMessage = text;
    }

    public final MutableText getJoinMessage() {
        return joinMessage != null ? joinMessage : new TranslatableText("multiplayer.player.joined", new Object[]{player.getDisplayName()}).formatted(Formatting.YELLOW);
    }

    public interface PlayerJoin {
        PlayerJoinEvent onPlayerJoin(ClientConnection connection, ServerPlayerEntity player, boolean isFirstJoin);
    }
}
