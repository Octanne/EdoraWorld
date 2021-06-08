package eu.octanne.edora.packet.client.packet;

import eu.octanne.edora.packet.MenuType;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class PacketClientValidateMenuData {
    
    private Identifier identifier;

    public PacketClientValidateMenuData(Identifier id) {
        identifier = id;
    }

    public void send(MenuType type, NbtCompound compoundData) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(type.getIndex());
        buf.writeNbt(compoundData);
        ClientPlayNetworking.send(identifier, buf);
    }
}
