package eu.octanne.edora.client.screen.menu.button;

import eu.octanne.edora.client.screen.menu.EdoraInventoryScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class NationButton extends ButtonWidget
{
    public enum NationEnum {
        Kawan(new TranslatableText("screen.edora.selectNation.nation.kawan"), 42, 166),
        Kallana(new TranslatableText("screen.edora.selectNation.nation.kallana"), 0, 166),
        Othala(new TranslatableText("screen.edora.selectNation.nation.othala"), 84, 166);
    
        int u;
        int v;
        Text name;
    
        NationEnum(Text name, int u, int v) {
            this.name = name;
            this.u = u;
            this.v = v;
        }
    
        public NationEnum getNationFromName(String name) {
            for(NationEnum enu : values()) {
                if(enu.name.asString().equals(name)) return enu;
            }
            return null;
        }

    }

    private NationEnum nation;

    public NationButton(int x, int y, NationEnum nation) {
        super(x, y, 41, 39,null,null);
        this.nation = nation;
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        if (this.isHovered()) {
            drawSelected(matrices, minecraftClient.getTextureManager(), this.x, this.y);
            minecraftClient.currentScreen.renderTooltip(matrices, nation.name, mouseX, mouseY);
        } else drawUnselected(matrices, minecraftClient.getTextureManager(), this.x, this.y);
    }

    @Override
    public void onPress() {
        // TODO MAKE TO OPEN NATION MENU
    }

    public void drawUnselected(MatrixStack matrices, TextureManager textureManager, int x, int y) {
        textureManager.bindTexture(EdoraInventoryScreen.EDORA_MAIN_MENU);
        matrices.push();
        matrices.translate((double)x, (double)y, 0.0D);
        drawTexture(matrices, 0, 0, nation.u, nation.v, 41, 39);
        matrices.pop();
     }

     public void drawSelected(MatrixStack matrices, TextureManager textureManager, int x, int y) {
        textureManager.bindTexture(EdoraInventoryScreen.EDORA_MAIN_MENU);
        matrices.push();
        matrices.translate((double)x, (double)y, 0.0D);
        drawTexture(matrices, 0, 0, nation.u, nation.v, width, height);
        // DRAW LINE AROUND BUTTON
        drawVerticalLine(matrices, 0, 0, height, 16711680);
        drawVerticalLine(matrices, width, 0, height, 16711680);
        drawHorizontalLine(matrices, 0, width, 0, 16711680);
        drawHorizontalLine(matrices, 0, width, height, 16711680);
        matrices.pop();
     }
}