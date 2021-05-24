package eu.octanne.edora.server.event;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

public class PlayerLeaveEvent {

    private ServerPlayerEntity player;
    private MutableText leftMessage = null;

    public PlayerLeaveEvent(ServerPlayerEntity player) {
        this.player = player;
    }

    public ServerPlayerEntity getPlayer() {
        return player;
    }

    public interface PlayerLeave {
        PlayerLeaveEvent onPlayerLeave(ServerPlayerEntity player);
    }

    public void setLeftMessage(MutableText text) {
        leftMessage = text;
    }

    public final MutableText getLeftMessage() {
        return leftMessage != null ? leftMessage : (new TranslatableText("multiplayer.player.left", new Object[]{this.player.getDisplayName()})).formatted(Formatting.YELLOW);
    }
}
