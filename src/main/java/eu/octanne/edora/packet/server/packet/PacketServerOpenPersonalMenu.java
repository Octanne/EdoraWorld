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
        if(pE.getNation() != null){
            if(forceNationChoose) {
                buf.writeInt(MenuType.NATION_SELECTOR.getIndex());
                tag.putString("nationName", pE.getNation().getName());
                buf.writeCompoundTag(tag);
            }else {
                buf.writeInt(MenuType.PERSONAL_MENU.getIndex());
                tag.putString("nationName", pE.getNation() != null ? pE.getNation().getName() : "none");
                tag.putString("townName", pE.getTown() != null ? pE.getTown().getName() : "none");
                tag.putString("guildeName", pE.getGuilde() != null ? pE.getGuilde().getName() : "none");
                tag.putInt("oannes", pE.getBankAccount().getOannes());
                buf.writeCompoundTag(tag);
            }
        }else{
            buf.writeInt(MenuType.NATION_SELECTOR.getIndex());
            buf.writeCompoundTag(tag);
        }
        ServerPlayNetworking.send(player, identifier, buf);
    }

}
