package eu.octanne.edora.packet.handler;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import eu.octanne.edora.packet.PacketIdentifiers;

public class PacketClientAskOpenMenu extends AbstractPacketClient {

    public PacketClientAskOpenMenu() {
        super("clientAskOpenMenu");
    }

	@Override
	public void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
			PacketByteBuf buf, PacketSender responseSender) {
		String menuName = buf.readString();
        server.execute(() -> {
            if(menuName.equals(MenuType.PERSONAL_MENU.getName())) {
                PacketIdentifiers.sendServerOpenPersonalMenu(player);
            }
            // TODO ADD FOR OTHER MENU
        });
	}

    public void sendPacket(MenuType type) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(type.getName());
        
        ClientPlayNetworking.send(identifier, buf);
    }
}
