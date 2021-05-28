package eu.octanne.edora.server.economy;

import java.util.List;
import java.util.Map;

public class BankAccount {
    
    private int nylus;
    private int oannes;

    public BankAccount(int oannes, int nylus){
        this.nylus = nylus;
        this.oannes = oannes;
    }

    public static BankAccount fromMap(Map<String, Integer> map)  {
        return new BankAccount(map.get("nylus"), map.get("oannes"));
    }

	public int getNylus() {
		return nylus;
	}

	public int getOannes() {
		return oannes;
	}

    public void setOannes(int i){
        oannes = i;
    }

    public void setNylus(int i) {
        nylus = i;
    }

    // TODO : Economy COMMANDS
}