package eu.octanne.edora.packet.client.handler;

import eu.octanne.edora.client.screen.menu.NationChooseMenuScreen;
import eu.octanne.edora.packet.MenuType;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.PlayChannelHandler;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;

public class HandlerServerPersonalMenu implements PlayChannelHandler {

	@Override
	public void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf,
			PacketSender responseSender) {
                int idType = buf.readInt();
                CompoundTag tag = buf.readCompoundTag();
                client.execute(() -> {
                    if(idType == MenuType.NATION_SELECTOR.getIndex()) {
                        String natName = tag.getString("factionName");
                        if(natName != null) natName = "none";
                        client.openScreen(new NationChooseMenuScreen(client.player,natName));
                    }else {
                        // TODO ADD OPEN PERSONAL MENU
                        client.player.sendChatMessage("Ouverture du menu personnel.");
                    }
                });
		
	}
    
}
