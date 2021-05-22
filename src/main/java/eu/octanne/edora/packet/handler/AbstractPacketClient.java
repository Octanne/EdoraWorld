package eu.octanne.edora.packet.handler;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.PlayChannelHandler;
import net.minecraft.util.Identifier;

public abstract class AbstractPacketClient implements PlayChannelHandler {
    
    protected Identifier identifier;

    public AbstractPacketClient(String identifier) {
        this.identifier = new Identifier(identifier);
    }

    public void register() {
        ServerPlayNetworking.registerGlobalReceiver(identifier,this);
    }
}
