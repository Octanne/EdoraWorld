package eu.octanne.edora.packet.client.handler;

import eu.octanne.edora.client.screen.menu.EdoraInventoryScreen;
import eu.octanne.edora.client.screen.menu.NationChooseMenuScreen;
import eu.octanne.edora.packet.MenuType;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.PlayChannelHandler;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;

public class HandlerServerPersonalMenu implements PlayChannelHandler {

	@Override
	public void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf,
			PacketSender responseSender) {
                int idType = buf.readInt();
                NbtCompound tag = buf.readNbt();
                client.execute(() -> {
                    if(idType == MenuType.NATION_SELECTOR.getIndex()) {
                        String natName = tag.getString("nationName");
                        if(natName != null) natName = "none";
                        client.openScreen(new NationChooseMenuScreen(client.player,natName));
                    }else if(idType == MenuType.PERSONAL_MENU.getIndex()) {
                        if(client.currentScreen instanceof EdoraInventoryScreen) {
                            EdoraInventoryScreen edoraScreen = (EdoraInventoryScreen) client.currentScreen;
                            edoraScreen.openEdoraMenu(tag);
                        }else {
                            client.openScreen(new InventoryScreen(client.player));
                            ((EdoraInventoryScreen)client.currentScreen).openEdoraMenu(tag);
                        }
                    }
                });
		
	}
    
}
