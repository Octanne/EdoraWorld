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

    public void send(ServerPlayerEntity player, boolean forceNationChoose) {
        CompoundTag tag = new CompoundTag();
        PacketByteBuf buf = PacketByteBufs.create();
        EdoraServerPlayerEntity pE = (EdoraServerPlayerEntity)player;
        if(pE.getNation() != null && !forceNationChoose){
            tag.putString("nationName", pE.getNation().getName());
            tag.putString("townName", pE.getTown().getName());
            tag.putString("guildeName", pE.getGuilde().getName());
            tag.putInt("oannes", pE.getBankAccount().getOannes());
            tag.putInt("nylus", pE.getBankAccount().getNylus());
        }
        
        if(tag.isEmpty()){
            buf.writeInt(MenuType.NATION_SELECTOR.getIndex());
            tag.putString("nationName", pE.getNation().getName());
            buf.writeCompoundTag(tag);
        }else{
            buf.writeInt(MenuType.PERSONAL_MENU.getIndex());
            buf.writeCompoundTag(tag);
        }
        ServerPlayNetworking.send(player, identifier, buf);
    }

}
