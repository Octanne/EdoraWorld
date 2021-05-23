package eu.octanne.edora.packet.client;

import eu.octanne.edora.packet.PacketIdentifiers;
import eu.octanne.edora.packet.client.packet.PacketClientAskOpenMenu;

public class PacketClients {

    static public PacketClientAskOpenMenu pcktClientAskOpenMenu = new PacketClientAskOpenMenu(PacketIdentifiers.pktClientAskOpenMenu);

}
