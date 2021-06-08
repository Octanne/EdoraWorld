package eu.octanne.edora.screenhandler;

import eu.octanne.edora.EdoraMain;
import eu.octanne.edora.item.items.BackpackItem;
import eu.octanne.edora.screenhandler.slot.BackpackSlot;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class BackpackScreenHandler extends ScreenHandler {

    private final ItemStack backpakItemStack;
    private SimpleInventory inventory;

    public BackpackScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, buf.readItemStack());
    }

    public BackpackScreenHandler(int syncId, PlayerInventory playerInventory, ItemStack backpackItemStack) {
        super(EdoraMain.BACKPACK_SCREEN_HANDLER, syncId);
        this.backpakItemStack = backpackItemStack;
        
        if(backpackItemStack.getItem() instanceof BackpackItem) {
            setupBackpack(playerInventory);
        } else {
            this.close(playerInventory.player);
        }
    }

    private void setupBackpack(PlayerInventory playerInventory) {
        NbtList tag = backpakItemStack.getOrCreateTag().getList("Inventory", NbtType.COMPOUND);
        inventory = new SimpleInventory(getItem().getNbSlots()) {
            @Override
            public void markDirty() {
                backpakItemStack.getOrCreateTag().put("Inventory", toTag(this));
                super.markDirty();
            }
        };

        fromTag(tag, inventory);

        if(getItem().getNbSlots() == 9) {
            int l;
            int c;
            //Backpack inventory
            for (l = 0; l < 3; ++l) {
                for (c = 0; c < 3; ++c) {
                    this.addSlot(new BackpackSlot(inventory, c + l * 3, 62 + c * 18, 17 + l * 18));
                }
            }
            //Player inventory
            for (l = 0; l < 3; ++l) {
                for (c = 0; c < 9; ++c) {
                    this.addSlot(new Slot(playerInventory, c + l * 9 + 9, 8 + c * 18, 84 + l * 18));
                }
            }
            //Player Hotbar
            for (c = 0; c < 9; ++c) {
                this.addSlot(new Slot(playerInventory, c, 8 + c * 18, 142));
            }
        } else if(getItem().getNbSlots() == 27) {
            int l;
            int c;
            //Backpack inventory
            for(l = 0; l < 3; ++l) {
                for(c = 0; c < 9; ++c) {
                    this.addSlot(new BackpackSlot(inventory, c + l * 9, 8 + c * 18, 18 + l * 18));
                }
            }
            //Player inventory
            for(l = 0; l < 3; ++l) {
                for(c = 0; c < 9; ++c) {
                    this.addSlot(new Slot(playerInventory, c + l * 9 + 9, 8 + c * 18, 84 + l * 18));
                }
            }
            //Player Hotbar
            for(c = 0; c < 9; ++c) {
                this.addSlot(new Slot(playerInventory, c, 8 + c * 18, 142));
            }
        } else if(getItem().getNbSlots() == 54) {
            int l;
            int c;
            //Backpack inventory
            for(l = 0; l < 6; ++l) {
                for(c = 0; c < 9; ++c) {
                    this.addSlot(new BackpackSlot(inventory, c + l * 9, 8 + c * 18, 18 + l * 18));
                }
            }
            //Player Hotbar
            for(l = 0; l < 3; ++l) {
                for(c = 0; c < 9; ++c) {
                    this.addSlot(new Slot(playerInventory, c + l * 9 + 9, 8 + c * 18, 140 + l * 18));
                }
            }
            //Player Hotbar
            for(c = 0; c < 9; ++c) {
                this.addSlot(new Slot(playerInventory, c, 8 + c * 18, 198));
            }
        }
    }

    public BackpackItem getItem() {
        return (BackpackItem) backpakItemStack.getItem();
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return backpakItemStack.getItem() instanceof BackpackItem;
    }

    public static NbtList toTag(SimpleInventory inventory) {
        NbtList tag = new NbtList();

        for(int i = 0; i < inventory.size(); i++) {
            NbtCompound stackTag = new NbtCompound();
            stackTag.putInt("Slot", i);
            stackTag.put("Stack", inventory.getStack(i).writeNbt(new NbtCompound()));
            tag.add(stackTag);
        }

        return tag;
    }

    public static void fromTag(NbtList tag, SimpleInventory inventory) {
        inventory.clear();

        tag.forEach(element -> {
            NbtCompound stackTag = (NbtCompound) element;
            int slot = stackTag.getInt("Slot");
            ItemStack stack = ItemStack.fromNbt(stackTag.getCompound("Stack"));
            inventory.setStack(slot, stack);
        });
    }

    // Shift + Player Inv Slot
    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }
    
            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
    
        return newStack;
    }
}