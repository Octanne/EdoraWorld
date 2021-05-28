package eu.octanne.edora.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

@Mixin(PlayerInventory.class)
public class MixinPlayerInventory {
    
    @Unique
    public DefaultedList<ItemStack> edoraSlot;
    
    @Inject(at = @At("RETURN"), method = "<init>(Lnet/minecraft/entity/player/PlayerEntity;)V")
    private void constructClass(PlayerInventory inventory, boolean onServer, PlayerEntity owner, CallbackInfo info){
        this.edoraSlot = DefaultedList.ofSize(3, ItemStack.EMPTY);
    }

    

}
