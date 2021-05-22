package eu.octanne.edora;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.util.Identifier;

public class EdoraMain {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "edora";
    public static final String MOD_NAME = "EdoraWorld";
    public static final Identifier MOD_FULL_ID = new Identifier(EdoraMain.MOD_ID, EdoraMain.MOD_ID);

    public static void log(Level level, String message) {
        LOGGER.log(level, "[" + MOD_NAME + "] " + message);
    }

}