package eu.octanne.edora.packet.server;

import eu.octanne.edora.packet.PacketIdentifiers;
import eu.octanne.edora.packet.server.packet.PacketServerOpenPersonalMenu;

public class PacketServers {
    
    static public PacketServerOpenPersonalMenu pcktServerOpenPersonalMenu = new PacketServerOpenPersonalMenu(PacketIdentifiers.pktServerOpenPersonalMenu);

}
