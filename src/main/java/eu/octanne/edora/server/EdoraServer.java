package eu.octanne.edora.server;

import com.mojang.brigadier.CommandDispatcher;

import org.apache.logging.log4j.Level;

import eu.octanne.edora.EdoraMain;
import eu.octanne.edora.item.EdoraItems;
import eu.octanne.edora.packet.PacketIdentifiers;
import eu.octanne.edora.server.event.ServerEvents;
import eu.octanne.edora.server.event.ServerEvents.*;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.command.ServerCommandSource;

public class EdoraServer implements DedicatedServerModInitializer {

    public EdoraServer() {
        super();
    }

    /**
     * DedicatedServer
     */
    @Override
    public void onInitializeServer() {
        // Register Events
        registerEvents();
        // Register Commands
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> registerCommands(dispatcher, dedicated));
        // Register PacketHandlers
        PacketIdentifiers.registerHandlerServer();
    }

    private void registerEvents() {
        ServerLifecycleEvents.SERVER_STARTING.register((minecraftServer) -> {
            onPreEnable();
        });
        ServerLifecycleEvents.SERVER_STARTED.register((minecraftServer) -> {
            onEnable();
        });

        ServerLifecycleEvents.SERVER_STOPPING.register((minecraftServer) -> {
            onPreDisable();
        });
        ServerLifecycleEvents.SERVER_STOPPED.register((minecraftServer) -> {
            onDisable();
        });
        ServerEvents.PLAYER_JOIN.register((server, connection, player) -> {
            onPlayerJoin(new PlayerJoinEvent(server, connection, player));
        });
    }

    private void onPreEnable() {
        EdoraMain.log(Level.INFO, "Loading of the plugin...");
        EdoraItems.registryItems();
        EdoraMain.log(Level.INFO, "The plugin has been loaded!");
    }

    private void onEnable() {
        EdoraMain.log(Level.INFO, "Loading of the plugin...");

        EdoraMain.log(Level.INFO, "The plugin has been loaded!");
    }

    private void onPreDisable() {
        EdoraMain.log(Level.INFO, "Unloading of the plugin...");

        EdoraMain.log(Level.INFO, "The plugin has been unloaded!");
    }

    private void onDisable() {
        EdoraMain.log(Level.INFO, "Disabling of the plugin...");

        EdoraMain.log(Level.INFO, "The plugin has been Disabled!");
    }

    private void onPlayerJoin(PlayerJoinEvent e) {
        EdoraMain.log(Level.INFO, "The player " + e.getPlayer().getName().getString() + " just connect to the server!");
    }

    /**
     * Fabric Wiki : https://fabricmc.net/wiki/tutorial:commands
     * Brigadier Wiki : https://github.com/Mojang/brigadier#registering-a-new-command
     */
    private void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        //GenmapCommand.register(dispatcher);
    }
}