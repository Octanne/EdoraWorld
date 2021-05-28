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
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.Level;

import eu.octanne.edora.EdoraMain;
import eu.octanne.edora.server.EdoraServer;
import eu.octanne.edora.server.economy.BankAccount;
import eu.octanne.edora.server.gourvern.nation.Nation.Gouvernment;
import eu.octanne.edora.server.gourvern.nation.Nation.NationBank;

public class NationsManager {
    private static String configFolderName = "nations";

    private static ArrayList<Nation> nationsList = new ArrayList<>();

    public static List<Nation> getNationsList() {
        return nationsList;
    }

    public static void loadAllNations() {
        String[] nationFileArray = EdoraServer.getConfigPath().resolve(configFolderName).toFile().list();
        createDefaultsNationsIfNotExist(nationFileArray);

        for(String nationFile : nationFileArray) {
            UUID uuid = UUID.fromString(FilenameUtils.removeExtension(nationFile));
            Nation nation = getNationFromJsonFile(uuid);
            nationsList.add(nation);
        }
    }

    private static void createDefaultsNationsIfNotExist(String[] nationFileArray) {
        List<String> nationFileList = Arrays.asList(nationFileArray);

        for(Nation nation : getDefaultNations()) {
            EdoraMain.log(Level.INFO, nation.getID().toString());
            if(!nationFileList.contains(nation.getID() + ".json")) {
                saveNationToJsonFile(nation);
            }
        }
    }

    private static Nation getNationFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Nation.class);
    }

    private static String getJsonFromNation(Nation nation) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(nation);
    }

    private static Boolean saveNationToJsonFile(Nation nation) {
        UUID uuid = nation.getID();
        String json = getJsonFromNation(nation);
        File configFile = getConfigFile(uuid);

        try {
            if(configFile.createNewFile()) EdoraMain.log(Level.INFO, "Erreur le fichier n'a pu être crée.");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(configFile), StandardCharsets.UTF_8));
            writer.write(json);
            writer.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static Nation getNationFromJsonFile(UUID uuid) {
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

    private static ArrayList<Nation> getDefaultNations() {
        ArrayList<Nation> defaultNations = new ArrayList<>();

        /***********Global Properties***********/
        ArrayList<UUID> globalCitizensID = new ArrayList<>();
        ArrayList<UUID> globalTownsID = new ArrayList<>();

        // Gouvernment
        Gouvernment globalGouvernment = new Gouvernment(null, null, null, null);

        // Bank
        BankAccount globalBankMainAccount = new BankAccount(0);
        BankAccount globalBankWarAccount = new BankAccount(0);
        BankAccount globalBankUrbaAccount = new BankAccount(0);
        BankAccount globalBankCommercialAccount = new BankAccount(0);
        NationBank globalBank = new NationBank(
            globalBankMainAccount,
            globalBankWarAccount,
            globalBankUrbaAccount,
            globalBankCommercialAccount
        );

        /***********Kallana***********/
        UUID kallanaId = UUID.fromString("23c6761c-443f-42c4-a5db-02ad7f85fc4b");
        String kallanaName = "Kallana";
        String kallanaSlogan = "adding a slogin here";

        Nation kallana = new Nation(
            kallanaId,
            kallanaName,
            kallanaSlogan,
            globalGouvernment,
            globalBank,
            globalCitizensID,
            globalTownsID
        );

        defaultNations.add(kallana);

        /***********Othala***********/
        UUID othalaId = UUID.fromString("c12d2130-52ae-43c5-bd42-642de372e4c8");
        String othalaName = "Othala";
        String othalaSlogan = "adding a slogin here";

        Nation othala = new Nation(
            othalaId,
            othalaName,
            othalaSlogan,
            globalGouvernment,
            globalBank,
            globalCitizensID,
            globalTownsID
        );

        defaultNations.add(othala);

        /***********Kawan***********/
        UUID kawanId = UUID.fromString("e73edf1a-1242-4455-bcbd-6dff2ce87089");
        String kawanName = "Kawan";
        String kawanSlogan = "adding a slogin here";

        Nation kawan = new Nation(
            kawanId,
            kawanName,
            kawanSlogan,
            globalGouvernment,
            globalBank,
            globalCitizensID,
            globalTownsID
        );

        defaultNations.add(kawan);

        return defaultNations;
    }
}
