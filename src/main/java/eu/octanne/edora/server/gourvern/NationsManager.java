package eu.octanne.edora.server.gourvern;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.logging.log4j.Level;

import eu.octanne.edora.EdoraMain;

public class NationsManager {
    private static ArrayList<Nation> nations;

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

    public static void test() {
        File nationFile = Paths.get("", "config", EdoraMain.MOD_ID, "nations", "simonination.json").toFile();   
        EdoraMain.log(Level.INFO, nationFile.toString());
        Nation nation = Nation.nationTest();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(nation);
        
        try {
            nationFile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(nationFile));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }
}
