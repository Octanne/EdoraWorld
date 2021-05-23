package eu.octanne.edora.packet.client.packet;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import eu.octanne.edora.packet.MenuType;
public class PacketClientAskOpenMenu {

    private Identifier identifier;

    public PacketClientAskOpenMenu(Identifier id) {
        identifier = id;
    }

    public void send(MenuType type) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(type.getIndex());
        
        ClientPlayNetworking.send(identifier, buf);
    }

}
