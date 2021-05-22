package eu.octanne.edora.packet.handler;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.PlayChannelHandler;
import net.minecraft.util.Identifier;

public abstract class AbstractPacketServer implements PlayChannelHandler {
    
    protected Identifier identifier;

    public AbstractPacketServer(String identifier) {
        this.identifier = new Identifier(identifier);
    }

    public void register() {
        ClientPlayNetworking.registerGlobalReceiver(identifier,this);
    }
}
