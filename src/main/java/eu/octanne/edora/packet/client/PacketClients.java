package eu.octanne.edora.packet.client;

import eu.octanne.edora.packet.PacketIdentifiers;
import eu.octanne.edora.packet.client.packet.PacketClientAskOpenMenu;
import eu.octanne.edora.packet.client.packet.PacketClientValidateMenuData;

public class PacketClients {

    public static final PacketClientAskOpenMenu pcktClientAskOpenMenu = new PacketClientAskOpenMenu(PacketIdentifiers.pktClientAskOpenMenu);
    public static final PacketClientValidateMenuData pcktClientValidateMenuData = new PacketClientValidateMenuData(PacketIdentifiers.pktClientValideMenuData);

}
