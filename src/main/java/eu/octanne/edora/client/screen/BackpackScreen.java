package eu.octanne.edora.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;

import eu.octanne.edora.item.items.BackpackItem;
import eu.octanne.edora.screenhandler.BackpackScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BackpackScreen extends HandledScreen<BackpackScreenHandler> {

    private Identifier TEXTURE;
    private BackpackItem backpackItem;
    
    public BackpackScreen(BackpackScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backpackItem = handler.getItem();

        if(backpackItem.getNbSlots() == 9) {
            this.TEXTURE = new Identifier("minecraft", "textures/gui/container/dispenser.png");
        } else if(backpackItem.getNbSlots() == 27) {
            this.TEXTURE = new Identifier("minecraft", "textures/gui/container/shulker_box.png");
        } else if(backpackItem.getNbSlots() == 54) {
            this.backgroundHeight = 222;
            this.playerInventoryTitleY = 128;
            this.TEXTURE = new Identifier("minecraft", "textures/gui/container/generic_54.png");
        }
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
        client.getTextureManager().bindTexture(TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
    
}
