package eu.octanne.edora.packet.handler;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class PacketServerOpenPersonalMenu extends AbstractPacketServer {

	public PacketServerOpenPersonalMenu() {
		super("clientOpenPersonalMenu");
	}

	@Override
	public void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf,
			PacketSender responseSender) {
        
		client.execute(() -> {

        });
		
	}

    public void sendPacket(ServerPlayerEntity player, CompoundTag tag) {
        PacketByteBuf buf = PacketByteBufs.create();
        
        if(tag.isEmpty()){
            buf.writeString(MenuType.NATION_SELECTOR.getName());
            buf.writeCompoundTag(tag);
        }else{
            buf.writeString(MenuType.PERSONAL_MENU.getName());
            buf.writeCompoundTag(tag);
        }
        ServerPlayNetworking.send(player, identifier, buf);
    }
}
