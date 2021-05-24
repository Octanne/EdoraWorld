package eu.octanne.edora.server.gourvern;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import eu.octanne.edora.server.economy.BankAccount;

public class Nation {

    private static ArrayList<Nation> nationsList = new ArrayList<>();

    public static Nation OTHALA = new Nation("Othala", "adding a slogan here", null);
    public static Nation KALLANA = new Nation("Kallana", "adding a slogan here", null);
    public static Nation KAWAN = new Nation("Kawan", "adding a slogan here", null);

    private UUID id;
    private String name;
    private String slogan;
    
    private Gouvernment gouvernment;
    private NationBank bank;

    private File jsonFile;

    private ArrayList<UUID> citizensID;
    private ArrayList<UUID> townsID;

    public static Nation getNationFromID(UUID id) {
        for(Nation nat : nationsList) if(nat.getID().equals(id)) return nat;
        return null;
    }

    public static Nation getNationFromName(String name) {
        for(Nation nat : nationsList) if(nat.getName().equals(name)) return nat;
        return null;
    }

    public Nation(String name, String slogan, UUID playerID){
        jsonFile = new File("/config/Nation/"+name+".json");
        if(jsonFile.exists()){
            loadFromFile();
        }else{
            id = UUID.randomUUID();
            this.name = name;
            this.slogan = slogan;
            bank = new NationBank();
            gouvernment = new Gouvernment(playerID);
        }
        nationsList.add(this);
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

    public NationBank getBank(){
        return bank;
    }

    public Gouvernment getGouv(){
        return gouvernment;
    }

    public List<UUID> getCitizensID(){
        return citizensID;
    }

    public List<UUID> getTownsID(){
        return townsID;
    }

    private boolean loadFromFile(){
        // TODO : loadData
        return false;
    }

    public boolean saveIntoFile(){
        // TODO : saveData
        return false;
    }
    
    protected class Gouvernment {

        private UUID leader, warLeader, urbaLeader, commercialLeader;

        Gouvernment(UUID leader){
            // Create
            this.leader = leader;
        }

        public void setLeaderID(UUID playerID){
            this.leader = playerID;
        }

        public UUID getLeaderID(){
            return leader;
        }

        public void setWarLeaderID(UUID playerID){
            this.warLeader = playerID;
        }

        public UUID getWarLeaderID(){
            return warLeader;
        }

        public void setUrbanLeaderID(UUID playerID){
            this.urbaLeader = playerID;
        }

        public UUID getUrbanLeaderID(){
            return urbaLeader;
        }

        public void setComercialLeaderID(UUID playerID){
            this.commercialLeader = playerID;
        }

        public UUID getComercialLeaderID(){
            return commercialLeader;
        }

    }
    public static class NationBank {

        private BankAccount mainAccount, warAccount, urbaAccount, commercialAccount;

        NationBank(){
            mainAccount = new BankAccount(0,0);
            warAccount = new BankAccount(0,0);
            urbaAccount = new BankAccount(0,0);
            commercialAccount = new BankAccount(0,0);
        }

        public BankAccount getMain(){
            return mainAccount;
        }
        public BankAccount getWar(){
            return warAccount;
        }
        public BankAccount getUrbanisation(){
            return urbaAccount;
        }
        public BankAccount geCommercial(){
            return commercialAccount;
        }
    }
}
