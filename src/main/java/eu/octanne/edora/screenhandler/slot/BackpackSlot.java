package eu.octanne.edora.screenhandler.slot;

import eu.octanne.edora.item.items.BackpackItem;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class BackpackSlot extends Slot{
    public BackpackSlot(Inventory inventory, int i, int x, int y) {
        super(inventory, i, x, y);
     }
  
     public boolean canInsert(ItemStack stack) {
        return !(stack.getItem() instanceof BackpackItem);
     }
}