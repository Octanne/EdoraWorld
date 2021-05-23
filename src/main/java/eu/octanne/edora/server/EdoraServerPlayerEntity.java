package eu.octanne.edora.server;

import eu.octanne.edora.server.economy.BankAccount;
import eu.octanne.edora.server.gourvern.Guilde;
import eu.octanne.edora.server.gourvern.Nation;
import eu.octanne.edora.server.gourvern.Town;
public interface EdoraServerPlayerEntity {

    Nation getNation();

    public void changeNation(Nation nation);

    Town getTown();

    public void changeTown(Town town);

    Guilde getGuilde();

    public void changeGuilde(Guilde guilde);

    BankAccount getBankAccount();
}
