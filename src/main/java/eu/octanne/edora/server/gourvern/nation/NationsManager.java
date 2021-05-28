package eu.octanne.edora.server.gourvern.nation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.Level;

import eu.octanne.edora.EdoraMain;
import eu.octanne.edora.server.EdoraServer;

public class NationsManager {
    private static String configFolderName = "nations";

    private static ArrayList<Nation> nationsList = new ArrayList<>();

    public static ArrayList<Nation> getNationsList() {
        return nationsList;
    }

    public static void loadAllNations() {
        String[] nationFileList = EdoraServer.getConfigPath().resolve(configFolderName).toFile().list();

        for(String nationFile : nationFileList) {
            UUID uuid = UUID.fromString(FilenameUtils.removeExtension(nationFile));
            Nation nation = getNationFromJsonFile(uuid);
            nationsList.add(nation);
            EdoraMain.log(Level.INFO, "Chargement de la nation " + nation.getName());
        }
    }

    public static Nation getNationFromJson(String json) {
        Gson gson = new Gson();
        Nation nation = gson.fromJson(json, Nation.class);
        return nation;
    }

    public static String getJsonFromNation(Nation nation) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(nation);
        return json;
    }

    public static Boolean saveNationToJsonFile(Nation nation) {
        UUID uuid = nation.getID();
        String json = getJsonFromNation(nation);
        File configFile = getConfigFile(uuid);

        try {
            configFile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(configFile), StandardCharsets.UTF_8));
            writer.write(json);
            writer.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }  
    }

    public static Nation getNationFromJsonFile(UUID uuid) {
        File configFile = getConfigFile(uuid);
        
        try {
            String json = new String(Files.readAllBytes(configFile.toPath()));
            return getNationFromJson(json);
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static File getConfigFile(UUID uuid) {
        return EdoraServer.getConfigPath().resolve(Paths.get(configFolderName, uuid + ".json")).toFile();
    }

    public static Nation getNationFromID(UUID uuid) {
        for(Nation nat : nationsList) if(nat.getID().equals(uuid)) return nat;
        return null;
    }

    public static Nation getNationFromName(String name) {
        for(Nation nat : nationsList) if(nat.getName().equals(name)) return nat;
        return null;
    }
}
