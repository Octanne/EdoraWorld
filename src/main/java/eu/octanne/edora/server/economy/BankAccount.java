package eu.octanne.edora.server.economy;

public class BankAccount {
    
    private int oannes;

    public BankAccount(int oannes){
        this.oannes = oannes;
    }

	public int getOannes() {
		return oannes;
	}

    public void setOannes(int i){
        oannes = i;
    }

    // TODO : Economy COMMANDS
}