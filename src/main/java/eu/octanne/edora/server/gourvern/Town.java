package eu.octanne.edora.server.gourvern;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import eu.octanne.edora.server.economy.BankAccount;
import net.minecraft.server.network.ServerPlayerEntity;

public class Town {

    private static ArrayList<Town> towns = new ArrayList<>();

    private UUID id;
    private String name;
    private String slogan;
    
    private UUID mayorID;
    private UUID[] adjointIDS = new UUID[5];
    private BankAccount bank;

    private File jsonFile;

    private ArrayList<UUID> citizensID;

    private Town(UUID id){
        this.id = id;
        loadFromFile();
    }

    private Town(String name, String slogan, ServerPlayerEntity mayorID){
        saveIntoFile();
    }

    private boolean loadFromFile(){
        // TODO : loadData
        return false;
    }

    public boolean saveIntoFile(){
        // TODO : saveData
        return false;
    }

    public UUID getID(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getSlogan(){
        return slogan;
    }

    public UUID getMayorID(){
        return mayorID;
    }

    public UUID[] getAdjointsID(){
        return adjointIDS;
    }

    public BankAccount getBank(){
        return bank;
    }

    public List<UUID> getCitizensID(){
        return citizensID.subList(0, citizensID.size());
    }

    public boolean removeCitizen(UUID id){
        if(citizensID.contains(id)){
            citizensID.remove(id);
            return true;
        }else return false;
    }

    public boolean addCitizen(UUID id){
        citizensID.add(id);
        return true;
    }

    public static final List<Town> getLoadTowns() {
        return towns;
    }

    // TODO NEED TO MAKE TAKE THE TOWN ON HARDDISK IF NOT FOUND AND AFTER IS NOT ON IT RETURN NULL
    public static Town getTownFromID(UUID uuid) {
        for(Town town : towns) if(town.getID().equals(uuid)) return town;
        return null;
    }

}