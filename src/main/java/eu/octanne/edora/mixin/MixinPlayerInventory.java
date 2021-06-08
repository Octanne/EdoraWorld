package eu.octanne.edora.mixin;

import java.util.List;

import com.google.common.collect.ImmutableList;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;
import net.minecraft.util.Nameable;
import net.minecraft.util.collection.DefaultedList;

@Mixin(PlayerInventory.class)
public class MixinPlayerInventory implements Inventory, Nameable {
    
    @Unique
    public DefaultedList<ItemStack> edoraSlot;
    
    @Shadow
    private List<DefaultedList<ItemStack>> combinedInventory;
    @Shadow public DefaultedList<ItemStack> main;
    @Shadow public DefaultedList<ItemStack> armor;
    @Shadow public DefaultedList<ItemStack> offHand;

    @Inject(at = @At("RETURN"), method = "<init>(Lnet/minecraft/entity/player/PlayerEntity;)V")
    private void constructClass(PlayerEntity owner, CallbackInfo info){
        this.edoraSlot = DefaultedList.ofSize(3, ItemStack.EMPTY);
        this.combinedInventory = ImmutableList.of(this.main, this.armor, this.offHand, this.edoraSlot);
    }

    @Inject(at = @At("RETURN"), method = "writeNbt(Lnet/minecraft/nbt/ListTag;)Lnet/minecraft/nbt/ListTag;")
    private void writeNbt(NbtList tag, CallbackInfoReturnable<NbtList> info) {
        NbtCompound compoundTag3;
        for(int k = 0; k < this.edoraSlot.size(); ++k) {
            if (!((ItemStack)this.edoraSlot.get(k)).isEmpty()) {
               compoundTag3 = new NbtCompound();
               compoundTag3.putByte("Slot", (byte)(k + 110));
               ((ItemStack)this.edoraSlot.get(k)).writeNbt(compoundTag3);
               tag.add(compoundTag3);
            }
        }
    }

    @Overwrite
    public void readNbt(NbtList tag) {
        this.main.clear();
        this.armor.clear();
        this.offHand.clear();
        this.edoraSlot.clear();

        for(int i = 0; i < tag.size(); ++i) {
            NbtCompound compoundTag = tag.getCompound(i);
            int j = compoundTag.getByte("Slot") & 255;
            ItemStack itemStack = ItemStack.fromNbt(compoundTag);
            if (!itemStack.isEmpty()) {
               if (j >= 0 && j < this.main.size()) {
                  this.main.set(j, itemStack);
               } else if (j >= 100 && j < this.armor.size() + 100) {
                  this.armor.set(j - 100, itemStack);
               } else if (j >= 110 && j < this.edoraSlot.size() + 110) {
                this.edoraSlot.set(j - 110, itemStack);
               } else if (j >= 150 && j < this.offHand.size() + 150) {
                  this.offHand.set(j - 150, itemStack);
               }
            }
         }
    }

    @Overwrite
    public int size() {
        int nbSize = 0;
        for(List<ItemStack> list : combinedInventory) {
            nbSize+=list.size();
        }
        return nbSize;
     }

    @Shadow
    public void clear() {
        // Auto-generated method stub
        
    }

    @Shadow
    public Text getName() {
        // Auto-generated method stub
        return null;
    }

    @Inject(at = @At("HEAD"), method = "isEmpty()Z")
    public void isEmpty(CallbackInfoReturnable<Boolean> info) {
       for(ItemStack itemstack : this.edoraSlot) {
          if(!itemstack.isEmpty()) {
              info.setReturnValue(false);
              return;
          }
       }
    }

    @Shadow
    public boolean isEmpty() {
        return false;
    }

    @Shadow
    public ItemStack getStack(int slot) {
        // Auto-generated method stub
        return null;
    }

    @Shadow
    public ItemStack removeStack(int slot, int amount) {
        // Auto-generated method stub
        return null;
    }

    @Shadow
    public ItemStack removeStack(int slot) {
        // Auto-generated method stub
        return null;
    }

    @Shadow
    public void setStack(int slot, ItemStack stack) {
        // Auto-generated method stub
        
    }

    @Shadow
    public void markDirty() {
        // Auto-generated method stub
        
    }

    @Shadow
    public boolean canPlayerUse(PlayerEntity player) {
        // Auto-generated method stub
        return false;
    }

}
