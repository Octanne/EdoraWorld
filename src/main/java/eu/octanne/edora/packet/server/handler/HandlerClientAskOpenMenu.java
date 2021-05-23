package eu.octanne.edora.packet.server.handler;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.PlayChannelHandler;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

import eu.octanne.edora.packet.MenuType;
import eu.octanne.edora.packet.server.PacketServers;

public class HandlerClientAskOpenMenu implements PlayChannelHandler {

	@Override
	public void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
    PacketByteBuf buf, PacketSender responseSender) {
                int idType = buf.readInt();
                server.execute(() -> {
                    if(idType == MenuType.PERSONAL_MENU.getIndex()) {
                        PacketServers.pcktServerOpenPersonalMenu.send(player,false);
                    }
                    // TODO ADD FOR OTHER MENU
                });
	}

}
