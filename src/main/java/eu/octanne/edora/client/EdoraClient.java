package eu.octanne.edora.client;

import org.lwjgl.glfw.GLFW;

import eu.octanne.edora.EdoraMain;
import eu.octanne.edora.client.screen.BackpackScreen;
import eu.octanne.edora.item.EdoraItems;
import eu.octanne.edora.packet.PacketIdentifiers;
import eu.octanne.edora.packet.client.handler.HandlerServerPersonalMenu;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class EdoraClient extends EdoraMain implements ClientModInitializer {
    
    /**
     * KeyBinding
     */
    static public final KeyBinding menuKeybind = new KeyBinding("key." + EdoraMain.MOD_ID + ".mainMenu", InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_M, "category." + EdoraMain.MOD_ID + ".main");


    public EdoraClient(){
        super();
    }
            
    /**
     * MinecraftClient (Solo)
     */
    @Override
    public void onInitializeClient() {
        // Register Items Group
        EdoraItems.registryItemGroups();
        // Register Items
        EdoraItems.registryItems();
        // Register Screens
        registerScreens();
        // Register Keybinds
        registerKeybinds();
        // Register PacketHandlers
        registerPacketHandlers();
    }

    private void registerPacketHandlers() {
        ClientPlayNetworking.registerGlobalReceiver(PacketIdentifiers.pktServerOpenPersonalMenu, 
            new HandlerServerPersonalMenu());
	}

	/**
     * Keybinds
     */
    private void registerKeybinds() {
        KeyBindingHelper.registerKeyBinding(menuKeybind);

    }

    private void registerScreens() {
        ScreenRegistry.register(EdoraMain.BACKPACK_SCREEN_HANDLER, BackpackScreen::new);
    }

	/**
     * MinecraftClient
     * @return client
     */
    static public MinecraftClient getClient() {
        return MinecraftClient.getInstance();
    }


}