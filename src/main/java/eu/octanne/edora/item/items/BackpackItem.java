package eu.octanne.edora.item.items;

import java.util.List;

import eu.octanne.edora.item.items.Settings.BackpackSettings;
import eu.octanne.edora.screenhandler.BackpackScreenHandler;
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
import net.minecraft.nbt.ListTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
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
            if(player instanceof EdoraServerPlayerEntity) {
                EdoraServerPlayerEntity edoraPlayer = (EdoraServerPlayerEntity)player;
                Nation playerNation = edoraPlayer.getNation();
                if(bSettings.getNationID() == null || (playerNation != null && playerNation.getID().equals(bSettings.getNationID()))) {
                    player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.PLAYERS,1.0f,1.0f);
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
                    player.sendMessage(new TranslatableText("message.edora.backpack.destroy"), true);
                    player.playSound(SoundEvents.UI_TOAST_IN, SoundCategory.PLAYERS, 5.0f, 1.5f);
                }
            } else {
                player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.PLAYERS,1.0f,1.0f);
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
