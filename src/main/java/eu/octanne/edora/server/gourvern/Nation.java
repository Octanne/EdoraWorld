package eu.octanne.edora.server.gourvern;

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

    public Nation() {

    }

    public static Nation nationTest() {
        Nation nation = new Nation();
        nation.id = UUID.randomUUID();
        nation.name = "SimoniNation";
        nation.slogan = "La nation de la mère patrie";

        nation.gouvernment = new Gouvernment(UUID.randomUUID());
        nation.gouvernment.warLeader = UUID.randomUUID();
        nation.gouvernment.urbaLeader = UUID.randomUUID();
        nation.gouvernment.commercialLeader = UUID.randomUUID();

        nation.bank = new NationBank();
        nation.bank.mainAccount = new BankAccount(1233, 32224);
        nation.bank.warAccount = new BankAccount(345, 13284834);
        nation.bank.urbaAccount = new BankAccount(883, 13249);
        nation.bank.commercialAccount = new BankAccount(474, 39034);

        return nation;
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
