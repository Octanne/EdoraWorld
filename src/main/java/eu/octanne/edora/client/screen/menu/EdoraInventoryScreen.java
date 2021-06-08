package eu.octanne.edora.client.screen.menu;

import org.spongepowered.asm.mixin.Unique;

import eu.octanne.edora.EdoraMain;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public interface EdoraInventoryScreen {
    
    @Unique
    static final Identifier EDORA_MAIN_MENU = new Identifier(EdoraMain.MOD_ID,"textures/gui/container/inv_main_menu.png");

    public boolean edoraMenuIsOpen();

    public void openEdoraMenu(NbtCompound dataTAG);

    public void closeEdoraMenu();

    public boolean toggleEdoraMenu();

    public void updateData(NbtCompound dataTAG);

}
