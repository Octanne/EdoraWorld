package eu.octanne.edora.server;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.mojang.brigadier.CommandDispatcher;

import org.apache.logging.log4j.Level;

import eu.octanne.edora.EdoraMain;
import eu.octanne.edora.packet.PacketIdentifiers;
import eu.octanne.edora.packet.server.handler.HandlerClientAskOpenMenu;
import eu.octanne.edora.packet.server.handler.HandlerClientValidateMenuData;
import eu.octanne.edora.server.event.PlayerJoinEvent;
import eu.octanne.edora.server.event.PlayerLeaveEvent;
import eu.octanne.edora.server.event.ServerEvents;
import eu.octanne.edora.server.gourvern.nation.NationsManager;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;

public class EdoraServer extends EdoraMain implements DedicatedServerModInitializer {

    private static MinecraftServer minecraftServer;

    private static Path configPath = Paths.get("config", EdoraMain.MOD_ID);

    public static final MinecraftServer getMinecraftServer() {
        return minecraftServer;
    }

    public static Path getConfigPath() {
        return configPath;
    }

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
        CommandRegistrationCallback.EVENT.register(this::registerCommands);
        // Register PacketHandlers
        registerPacketHandlers();

        // init Client Server Share Part
        onInitialize();
    }

    private void registerPacketHandlers() {
        ServerPlayNetworking.registerGlobalReceiver(PacketIdentifiers.pktClientAskOpenMenu, 
        new HandlerClientAskOpenMenu());
        ServerPlayNetworking.registerGlobalReceiver(PacketIdentifiers.pktClientValideMenuData, 
        new HandlerClientValidateMenuData());
	}

    private void registerEvents() {
        ServerLifecycleEvents.SERVER_STARTING.register(this::onPreEnable);
        ServerLifecycleEvents.SERVER_STARTED.register(this::onEnable);
        ServerLifecycleEvents.SERVER_STOPPING.register(this::onPreDisable);
        ServerLifecycleEvents.SERVER_STOPPED.register(this::onDisable);

        ServerEvents.PLAYER_JOIN.register((connection, player, isFirstJoin) -> onPlayerJoin(new PlayerJoinEvent(connection,player,isFirstJoin)));
        ServerEvents.PLAYER_LEAVE.register(player -> onPlayerLeave(new PlayerLeaveEvent(player)));
    }

    private void onPreEnable(MinecraftServer server) {
        EdoraMain.log(Level.INFO, "Loading of the plugin...");
        minecraftServer = server;
        EdoraMain.log(Level.INFO, "The plugin has been loaded!");
    }

    private void onEnable(MinecraftServer server) {
        EdoraMain.log(Level.INFO, "Loading of the plugin...");

        NationsManager.loadAllNations();

        EdoraMain.log(Level.INFO, "The plugin has been loaded!");
    }

    private void onPreDisable(MinecraftServer server) {
        EdoraMain.log(Level.INFO, "Unloading of the plugin...");

        EdoraMain.log(Level.INFO, "The plugin has been unloaded!");
    }

    private void onDisable(MinecraftServer server) {
        EdoraMain.log(Level.INFO, "Disabling of the plugin...");

        EdoraMain.log(Level.INFO, "The plugin has been Disabled!");
    }

    private PlayerJoinEvent onPlayerJoin(PlayerJoinEvent e) {
        MutableText message = e.getPlayer().getDisplayName().copy();
        message.append(new LiteralText(" viens de rejoindre Edora."));
        message.formatted(Formatting.GREEN);
        e.setJoinMessage(message);
        return e;
    }

    private PlayerLeaveEvent onPlayerLeave(PlayerLeaveEvent e) {
        MutableText message = e.getPlayer().getDisplayName().copy();
        message.append(new LiteralText(" viens de quitter Edora."));
        message.formatted(Formatting.RED);
        e.setLeftMessage(message);
        return e;
    }


    /**
     * Fabric Wiki : https://fabricmc.net/wiki/tutorial:commands
     * Brigadier Wiki : https://github.com/Mojang/brigadier#registering-a-new-command
     */
    private void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        //GenmapCommand.register(dispatcher);

    }
}