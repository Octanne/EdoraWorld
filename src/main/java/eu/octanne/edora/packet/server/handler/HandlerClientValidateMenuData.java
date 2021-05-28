package eu.octanne.edora.packet.server.handler;

import eu.octanne.edora.packet.MenuType;
import eu.octanne.edora.server.EdoraServerPlayerEntity;
import eu.octanne.edora.server.gourvern.nation.Nation;
import eu.octanne.edora.server.gourvern.nation.NationsManager;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.PlayChannelHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;

public class HandlerClientValidateMenuData implements PlayChannelHandler {
    
	@Override
	public void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
    PacketByteBuf buf, PacketSender responseSender) {
                int idType = buf.readInt();
                CompoundTag tag = buf.readCompoundTag();
                server.execute(() -> {
                    if(idType == MenuType.NATION_SELECTOR.getIndex()) {
                        EdoraServerPlayerEntity ePlayer = (EdoraServerPlayerEntity) player;
                        String name = tag.getString("chooseNation");
                        Nation nation = NationsManager.getNationFromName(name);
                        if(nation != null){
                            ePlayer.changeNation(nation);
                            player.sendMessage(new LiteralText("Choix de la Nation validée."), true);
                        }else{
                            player.sendMessage(new LiteralText("Erreur lors du traitement de votre réponse."), true);
                        }
                    }
                    // TODO ADD FOR OTHER MENU
                });
	}
}
