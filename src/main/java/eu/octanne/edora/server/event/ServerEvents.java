package eu.octanne.edora.server.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.ChunkPos;

public class ServerEvents {

    public static final Event<PlayerJoin> PLAYER_JOIN = EventFactory.createArrayBacked(PlayerJoin.class,
            callbacks -> (server, connection, entity) -> {
                for (PlayerJoin callback : callbacks) {
                    callback.onPlayerJoin(server, connection, entity);
                }
            });

    public interface PlayerJoin {
        void onPlayerJoin(MinecraftServer server, ClientConnection connection, ServerPlayerEntity player);
    }

    static public class PlayerJoinEvent {

        private MinecraftServer server;
        private ClientConnection connection;
        private ServerPlayerEntity player;

        public PlayerJoinEvent(MinecraftServer server, ClientConnection connection, ServerPlayerEntity player) {
            this.connection = connection;
            this.server = server;
            this.player = player;
        }

        public MinecraftServer getServer() {
            return server;
        }

        public ClientConnection getConnection(){

            return connection;
        }

        public ServerPlayerEntity getPlayer(){
            return player;
        }
    }


    //PLAYER_MOVED event (when a player move)
    public static final Event<PlayerMoved> PLAYER_MOVED = EventFactory.createArrayBacked(PlayerMoved.class,
        (listeners) -> (player) -> {
            for (PlayerMoved listener : listeners) {
                listener.playerMoved(player);
            }
    });

    public interface PlayerMoved {
        void playerMoved(PlayerEntity player);
    }

    static public class PlayerMovedEvent {
        private PlayerEntity player;

        public PlayerMovedEvent(PlayerEntity player) {
            this.player = player;
        }

        public PlayerEntity getPlayer(){ return player; }
    }


    //PLAYER_MOVED_CHUNK event (when a player move to another chunk)
    public static final Event<PlayerMovedChunk> PLAYER_MOVED_CHUNK = EventFactory.createArrayBacked(PlayerMovedChunk.class,
        (listeners) -> (player, oldPos, newPos) -> {
            for (PlayerMovedChunk listener : listeners) {
                listener.playerMovedChunk(player, oldPos, newPos);
            }
    });

    public interface PlayerMovedChunk {
        void playerMovedChunk(PlayerEntity player, ChunkPos oldPos, ChunkPos newPos);
    }

    static public class PlayerMovedChunkEvent {
        private PlayerEntity player;
        private ChunkPos oldPos;
        private ChunkPos newPos;

        public PlayerMovedChunkEvent(PlayerEntity player, ChunkPos oldPos, ChunkPos newPos) {
            this.player = player;
            this.oldPos = oldPos;
            this.newPos = newPos;
        }

        public PlayerEntity getPlayer(){ return player; }
        public ChunkPos getOldPos(){ return oldPos; }
        public ChunkPos getNewPos(){ return newPos; }
    }


    //CHUNK_GENERATED event (when the SURFACE of a chunk is generated)
    public static final Event<ChunkGenerated> CHUNK_GENERATED = EventFactory.createArrayBacked(ChunkGenerated.class,
        (listeners) -> (chunkPos) -> {
            for (ChunkGenerated listener : listeners) {
                listener.chunkGenerated(chunkPos);
            }
    });

    public interface ChunkGenerated {
        void chunkGenerated(ChunkPos chunkPos);
    }

    static public class ChunkGeneratedEvent {
        private ChunkPos chunkPos;

        public ChunkGeneratedEvent(ChunkPos chunkPos) {
            this.chunkPos = chunkPos;
        }

        public ChunkPos getPos(){
            return chunkPos;
        }
    }
}