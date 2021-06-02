package eu.octanne.edora.item.items;

import eu.octanne.edora.client.screen.BackpackScreen;
import eu.octanne.edora.screenHandler.BackpackScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BackpackItem extends Item {
    private int nbSlots;

    public BackpackItem(Settings settings, int nbSlots) {
        super(settings);
        this.nbSlots = nbSlots;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack backpackItemStack = player.getStackInHand(hand);
        if(!world.isClient) {
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

        return TypedActionResult.pass(player.getStackInHand(hand));
    }

    public int getNbSlots() {
        return nbSlots;
    }
    
}
