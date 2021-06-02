package eu.octanne.edora.item.items;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.lwjgl.system.CallbackI.I;

import eu.octanne.edora.EdoraMain;
import eu.octanne.edora.item.items.Settings.BackpackSettings;
import eu.octanne.edora.screenHandler.BackpackScreenHandler;
import eu.octanne.edora.server.EdoraServerPlayerEntity;
import eu.octanne.edora.server.gourvern.nation.Nation;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BackpackItem extends Item {
    private BackpackSettings bSettings;
    private ItemStack backpackItemStack;

    public BackpackItem(Settings settings, BackpackSettings bSettings) {
        super(settings);
        this.bSettings = bSettings;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        backpackItemStack = player.getStackInHand(hand);
        
        if(!world.isClient) {
            EdoraServerPlayerEntity edoraPlayer = (EdoraServerPlayerEntity)player;
            Nation playerNation = edoraPlayer.getNation();
            if((playerNation.getID().equals(bSettings.getNationID())) || (bSettings.getNationID() == null)) {
                player.openHandledScreen(new ExtendedScreenHandlerFactory(){
    
                    @Override
                    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
                        buf.writeItemStack(backpackItemStack);
                    }
    
                    @Override
                    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
                        return new BackpackScreenHandler(syncId, inv, backpackItemStack);
                    }
    
                    @Override
                    public Text getDisplayName() {
                        return new TranslatableText(backpackItemStack.getTranslationKey());
                    }
                    
                });
            } else {
                wrongNation(world, player);
                player.setStackInHand(hand, ItemStack.EMPTY);
                // player.sendMessage(Text.of("Ce backpack appartient à une autre nation, il s'est détruit"), true);
            }
        }

        return TypedActionResult.pass(player.getStackInHand(hand));
    }

    public void wrongNation(World world, PlayerEntity player) {
        SimpleInventory bInventory = new SimpleInventory(getNbSlots());
        ListTag inventoryTag = backpackItemStack.getOrCreateTag().getList("Inventory", NbtType.COMPOUND);
        BackpackScreenHandler.fromTag(inventoryTag, bInventory);

        List<ItemStack> backpackItems = bInventory.clearToList();
        for (ItemStack backpackItem : backpackItems) {
            ItemEntity itemEntity = new ItemEntity(world, player.getX(), player.getY(), player.getZ(), backpackItem);
            world.spawnEntity(itemEntity);
        }
    }

    public int getNbSlots() {
        return bSettings.getNbSlots();
    }
    
}
