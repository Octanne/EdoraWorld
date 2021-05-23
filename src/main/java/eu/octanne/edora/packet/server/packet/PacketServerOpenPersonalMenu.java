package eu.octanne.edora.packet.server.packet;

import eu.octanne.edora.packet.MenuType;
import eu.octanne.edora.server.EdoraServerPlayerEntity;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class PacketServerOpenPersonalMenu {
    
    private Identifier identifier;

    public PacketServerOpenPersonalMenu(Identifier id) {
        identifier = id;
    }

    public void send(ServerPlayerEntity player) {
        CompoundTag tag = new CompoundTag();
        EdoraServerPlayerEntity pE = (EdoraServerPlayerEntity)player;
        if(pE.getNation() != null){
            tag.putString("nationName", pE.getNation().getName());
            tag.putString("townName", pE.getTown().getName());
            tag.putString("guildeName", pE.getGuilde().getName());
            tag.putInt("oannes", pE.getBankAccount().getOannes());
            tag.putInt("nylus", pE.getBankAccount().getNylus());
        }
        PacketByteBuf buf = PacketByteBufs.create();
        
        if(tag.isEmpty()){
            buf.writeInt(MenuType.NATION_SELECTOR.getIndex());
            buf.writeCompoundTag(tag);
        }else{
            buf.writeInt(MenuType.PERSONAL_MENU.getIndex());
            buf.writeCompoundTag(tag);
        }
        ServerPlayNetworking.send(player, identifier, buf);
    }

}
