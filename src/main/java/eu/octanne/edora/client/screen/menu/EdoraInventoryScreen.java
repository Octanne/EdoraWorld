package eu.octanne.edora.client.screen.menu;

import net.minecraft.nbt.CompoundTag;

public interface EdoraInventoryScreen {
    
    public void openEdoraMenu(CompoundTag dataTAG);

    public void closeEdoraMenu();

    public boolean toggleEdoraMenu();

    public void updateData(CompoundTag dataTAG);

}
