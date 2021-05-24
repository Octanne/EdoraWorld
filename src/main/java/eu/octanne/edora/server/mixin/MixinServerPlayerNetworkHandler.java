package eu.octanne.edora.server.mixin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import eu.octanne.edora.server.event.ServerEvents;
import net.minecraft.network.MessageType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.filter.TextStream;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;

@Mixin(ServerPlayNetworkHandler.class)
public class MixinServerPlayerNetworkHandler {

    @Shadow private static final Logger LOGGER = LogManager.getLogger();
    @Shadow MinecraftServer server;
    @Shadow ServerPlayerEntity player;

    @Shadow
    private boolean isHost() {
        return false;
    }

    public void onDisconnected(Text reason) {
        LOGGER.info("{} lost connection: {}", this.player.getName().getString(), reason.getString());
        this.server.forcePlayerSampleUpdate();
        this.server.getPlayerManager().broadcastChatMessage((new TranslatableText("multiplayer.player.left", new Object[]{this.player.getDisplayName()})).formatted(Formatting.YELLOW), MessageType.SYSTEM, Util.NIL_UUID);
        this.player.onDisconnect();
        this.server.getPlayerManager().remove(this.player);
        TextStream textStream = this.player.getTextStream();
        if (textStream != null)
            textStream.onDisconnect();
        if (isHost()) {
            LOGGER.info("Stopping singleplayer server as player logged out");
            this.server.stop(false);
        }
        ServerEvents.PLAYER_LEAVE.invoker().onPlayerLeave(player);
    }

}
