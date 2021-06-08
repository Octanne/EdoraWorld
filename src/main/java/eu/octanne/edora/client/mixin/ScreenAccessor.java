package eu.octanne.edora.client.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.Drawable;

@Mixin(Screen.class)
public interface ScreenAccessor {
    @Accessor
    List<Drawable> getDrawables();
}
