package eu.octanne.edora.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeFinder;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.slot.Slot;

@Mixin(PlayerScreenHandler.class)
public class MixinPlayerScreenHandler extends AbstractRecipeScreenHandler<CraftingInventory> {
    
    // That for that
    public MixinPlayerScreenHandler() {
        super(null,0);
    }

    @Inject(at = @At("RETURN"), method = "<init>(Lnet/minecraft/entity/player/PlayerInventory;ZLnet/minecraft/entity/player/PlayerEntity;)V")
    private void constructClass(PlayerInventory inventory, boolean onServer, PlayerEntity owner, CallbackInfo info){
        for(int n = 0; n < 3; ++n) {
            this.addSlot(new Slot(inventory /* change for create new one ,*/, 40, 77, 8 + n * 18));
        }
    }

    @Shadow
    public void populateRecipeFinder(RecipeFinder finder) {
        // Auto-generated method stub
    }

    @Shadow
    public void clearCraftingSlots() {
        // Auto-generated method stub
    }

    @Shadow
    public boolean matches(Recipe<? super CraftingInventory> recipe) {
        return false;
    }

    @Shadow
    public int getCraftingResultSlotIndex() {
        return 0;
    }

    @Shadow
    public int getCraftingWidth() {
        return 0;
    }

    @Shadow
    public int getCraftingHeight() {
        return 0;
    }

    @Override
    public int getCraftingSlotCount() {
        return 0;
    }

    @Shadow
    public RecipeBookCategory getCategory() {
        return null;
    }

    @Shadow
    public boolean canUse(PlayerEntity player) {
        return false;
    }

    
}
