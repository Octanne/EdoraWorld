package eu.octanne.edora.server.gourvern.nation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import eu.octanne.edora.server.economy.BankAccount;

public class Nation {

    private UUID id;
    private String name;
    private String slogan;
    
    private Gouvernment gouvernment;
    private NationBank bank;

    private ArrayList<UUID> citizensID;
    private ArrayList<UUID> townsID;

    public Nation(UUID id, String name, String slogan, Gouvernment gouvernment, NationBank bank, ArrayList<UUID> citizensID, ArrayList<UUID> townsID) {
        this.id = id;
        this.name = name;
        this.slogan = slogan;
        this.gouvernment = gouvernment;
        this.bank = bank;
        this.citizensID = citizensID;
        this.townsID = townsID;
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
    
    protected static class Gouvernment {

        private UUID leader, warLeader, urbaLeader, commercialLeader;

        Gouvernment(UUID leader, UUID warLeader, UUID urbaLeader, UUID commercialLeader){
            this.leader = leader;
            this.warLeader = warLeader;
            this.urbaLeader = urbaLeader;
            this.commercialLeader = commercialLeader;
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

        NationBank(BankAccount mainAccount, BankAccount warAccount, BankAccount urbaAccount, BankAccount commercialAccount){
            this.mainAccount = mainAccount;
            this.warAccount = warAccount;
            this.urbaAccount = urbaAccount;
            this.commercialAccount = commercialAccount;
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
