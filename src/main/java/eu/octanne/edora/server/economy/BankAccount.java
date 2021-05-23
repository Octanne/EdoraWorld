package eu.octanne.edora.server.economy;

public class BankAccount {
    
    private int nylus;
    private int oannes;

    public BankAccount(int oannes, int nylus){
        this.nylus = nylus;
        this.oannes = oannes;
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