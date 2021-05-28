package eu.octanne.edora.server.gourvern;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import eu.octanne.edora.server.economy.BankAccount;
import net.minecraft.server.network.ServerPlayerEntity;

public class Guilde {

    private static ArrayList<Guilde> guildes = new ArrayList<>();

    private UUID id;
    private String name;
    private String slogan;
    
    private UUID chiefID;
    private UUID[] adjointIDS = new UUID[5];
    private BankAccount bank;

    private File jsonFile;

    private ArrayList<UUID> membersID;

    private Guilde(UUID id){
        this.id = id;
        loadFromFile();
    }

    private Guilde(String name, String slogan, ServerPlayerEntity player){
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

    public UUID getChiefID(){
        return chiefID;
    }

    public UUID[] getAdjointsID(){
        return adjointIDS;
    }

    public BankAccount getBank(){
        return bank;
    }

    public List<UUID> getMembersID(){
        return membersID.subList(0, membersID.size());
    }

    public boolean removeCitizen(UUID id){
        if(membersID.contains(id)){
            membersID.remove(id);
            return true;
        }else return false;
    }

    public boolean addMember(UUID id){
        membersID.add(id);
        return true;
    }

    public static final List<Guilde> getLoadGuildes() {
        return guildes;
    }

    // TODO NEED TO MAKE TAKE THE TOWN ON HARDDISK IF NOT FOUND AND AFTER IS NOT ON IT RETURN NULL
    public static Guilde getGuildeFromID(UUID uuid) {
        for(Guilde guilde : guildes) if(guilde.getID().equals(uuid)) return guilde;
        return null;
    }

}