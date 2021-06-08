package eu.octanne.edora.mixin;

import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import eu.octanne.edora.EdoraMain;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeMatcher;
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
            this.addSlot(new Slot(inventory, 41 + n, 77, 8 + n * 18));
        }
        EdoraMain.log(Level.INFO, "Add 3 new slots!");
    }

    @Shadow
    public void populateRecipeFinder(RecipeMatcher finder) {
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
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Shadow
    @Environment(EnvType.CLIENT)
    @Override
    public RecipeBookCategory getCategory() {
        // Auto-generated method stub
        return null;
    }

    @Override
    public boolean canInsertIntoSlot(int index) {
        return true;
    }

    
}
