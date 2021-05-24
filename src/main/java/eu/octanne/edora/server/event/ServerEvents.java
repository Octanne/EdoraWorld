package eu.octanne.edora.server.event;

import eu.octanne.edora.server.event.PlayerJoinEvent.PlayerJoin;
import eu.octanne.edora.server.event.PlayerLeaveEvent.PlayerLeave;
import eu.octanne.edora.server.event.PlayerMoveChunkEvent.PlayerMoveChunk;
import eu.octanne.edora.server.event.PlayerMoveEvent.PlayerMove;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public class ServerEvents {
    public static final Event<PlayerJoin> PLAYER_JOIN = EventFactory.createArrayBacked(PlayerJoin.class,
        listeners -> (connection, entity, isFirstJoin) -> {
            for (PlayerJoin listener : listeners) {
                listener.onPlayerJoin(connection, entity, isFirstJoin);
            }
        });
    public static final Event<PlayerLeave> PLAYER_LEAVE = EventFactory.createArrayBacked(PlayerLeave.class,
        listeners -> (entity) -> {
            for (PlayerLeave listener : listeners) {
                listener.onPlayerLeave(entity);
            }
        });
    public static final Event<PlayerMove> PLAYER_MOVED = EventFactory.createArrayBacked(PlayerMove.class,
        listeners -> (player) -> {
            for (PlayerMove listener : listeners) {
               listener.onPlayerMove(player);
            }
        });
    public static final Event<PlayerMoveChunk> PLAYER_MOVED_CHUNK = EventFactory.createArrayBacked(PlayerMoveChunk.class,
        listeners -> (player, oldPos, newPos) -> {
            for (PlayerMoveChunk listener : listeners) {
                listener.onPlayerMoveChunk(player, oldPos, newPos);
            }
        });
    

}