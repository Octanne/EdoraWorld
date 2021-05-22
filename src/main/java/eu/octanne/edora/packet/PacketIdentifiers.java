package eu.octanne.edora.packet;

import eu.octanne.edora.packet.handler.MenuType;
import eu.octanne.edora.packet.handler.PacketClientAskOpenMenu;
import eu.octanne.edora.packet.handler.PacketServerOpenPersonalMenu;
import eu.octanne.edora.server.mixin.accessor.ServerPlayerEntityAccessor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerPlayerEntity;

public class PacketIdentifiers {

    private static PacketClientAskOpenMenu CLIENT_ASK_OPEN_MENU;
    
    private static PacketServerOpenPersonalMenu SERVER_OPEN_PERSONAL_MENU;
    
    static public void registerHandlerServer() {
        CLIENT_ASK_OPEN_MENU = new PacketClientAskOpenMenu();
    }

    static public void registerHandlerClient() {
        
    }

    static public void sendClientAskOpenMenu(MenuType type) {
        CLIENT_ASK_OPEN_MENU.sendPacket(type);
    }

	public static void sendServerOpenPersonalMenu(ServerPlayerEntity player) {
        CompoundTag tag = new CompoundTag();
        ServerPlayerEntityAccessor pE = (ServerPlayerEntityAccessor)player;
        if(pE.getNation() != null){
            tag.putString("nationName", pE.getNation().getName());
            tag.putString("townName", pE.getTown().getName());
            tag.putString("guildeName", pE.getGuilde().getName());
            tag.putInt("oannes", pE.getBankAccount().getOannes());
            tag.putInt("nylus", pE.getBankAccount().getNylus());
        }
        SERVER_OPEN_PERSONAL_MENU.sendPacket(player, tag);
	}
    
}