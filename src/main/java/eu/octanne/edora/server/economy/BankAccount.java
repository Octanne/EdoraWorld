package eu.octanne.edora.server.economy;

import java.util.Map;

public class BankAccount {
    
    private int oannes;

    public BankAccount(int oannes){
        this.oannes = oannes;
    }

    public static BankAccount fromMap(Map<String, Integer> map)  {
        return new BankAccount(map.get("oannes"));
    }

	public int getOannes() {
		return oannes;
	}

    public void setOannes(int i){
        oannes = i;
    }

    // TODO : Economy COMMANDS
}