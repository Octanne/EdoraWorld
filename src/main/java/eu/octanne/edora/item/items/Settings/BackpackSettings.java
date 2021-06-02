package eu.octanne.edora.item.items.Settings;

import java.util.UUID;

public class BackpackSettings {
    private final int nbSlots;
    private final UUID nationID;

    public BackpackSettings(int nbSlots, UUID nationID) {
        this.nbSlots = nbSlots;
        this.nationID = nationID;
    }

    public BackpackSettings(int nbSlots) {
        this.nbSlots = nbSlots;
        this.nationID = null;
    }

    public int getNbSlots() {
        return nbSlots;
    }

    public UUID getNationID() {
        return nationID;
    }
}
