package eu.octanne.edora.client.screen.menu;

import eu.octanne.edora.EdoraMain;
import eu.octanne.edora.client.EdoraClient;
import eu.octanne.edora.client.screen.menu.NationChooseMenuScreen.NationButtonWidget;
import eu.octanne.edora.client.screen.menu.NationChooseMenuScreen.NationEnum;
import eu.octanne.edora.packet.MenuType;
import eu.octanne.edora.packet.client.PacketClients;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public class NationChooseMenuScreen extends/*<T extends ScreenHandler> extends HandledScreen<T>*/ Screen {

    private static final Identifier TEXTURE = new Identifier(EdoraMain.MOD_ID, "textures/gui/container/nation_choix.png");
    NationEnum selected = null;

    public NationChooseMenuScreen(PlayerEntity player, String natName) {
        super(Text.Serializer.fromJson("{\"text\":\""+new TranslatableText("screen."+EdoraMain.MOD_ID+".playermenu.title")+"\"}"));
        if(!natName.equals("none")) {
            for(NationEnum nation : NationEnum.values()) {
                if(nation.name.asString().equals(natName)){
                    selected = nation;
                    break;
                }
            }
        }
    }
    
    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    protected void init() {
        super.init();

        // Coordonnées du GUI (centré)
        int i = this.width / 2 - 73;
        int j = this.height / 2 - 83;

        // Création du bouton en bas à droite
        DoneButtonWidget doneButton = new DoneButtonWidget(i + 122, j + 141);
        this.addDrawableChild(doneButton);

        /**
         * Boucle pour créer tous les boutons représentant les Nation définis dans this.Nation
         * la variable newButtonHeight augmente de 32px à chaques tours et permet de mettre les boutons les uns au dessous des autres.
         */
        int newButtonHeight = 0;
        for (NationEnum nation : NationEnum.values()) {
            NationButtonWidget nationButtonWidget = new NationButtonWidget(i + 8, j + 28 + newButtonHeight, nation);
            this.addDrawableChild(nationButtonWidget);
            newButtonHeight += 32;
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (EdoraClient.menuKeybind.matchesKey(keyCode, scanCode) || this.client.options.keyInventory.matchesKey(keyCode, scanCode)) {
            this.onClose();
            return true;
        }else {
            return super.keyPressed(keyCode, scanCode, modifiers);
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        // Chargement de la texture
        this.client.getTextureManager().bindTexture(TEXTURE);

        // Fond foncé derrière le GUI
        this.renderBackground(matrices, 0);

        // Coordonnées du GUI (centré)
        int i = this.width / 2 - 73;
        int j = this.height / 2 - 83;

        // Affichage du GUI
        drawTexture(matrices, i, j, 1, 1, 147, 166, 512, 512);

        // Render du titre du GUI
        drawCenteredText(matrices, this.textRenderer, new TranslatableText("screen.edora.selectNation.title"), this.width / 2, this.height / 2 - 73, -1);
        
        super.render(matrices, mouseX, mouseY, delta);
    }

    public class DoneButtonWidget extends ButtonWidget {

        public DoneButtonWidget(int x, int y) {
            super(x, y, 20, 20, new TranslatableText("screen.edora.selectNation.title"), null);
        }

        @Override
        public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            MinecraftClient minecraftClient = MinecraftClient.getInstance();
            if (this.isHovered()) {
                this.drawSelected(matrices, minecraftClient.getTextureManager());
                renderTooltip(matrices, new TranslatableText("screen.edora.selectNation.ok"), mouseX, mouseY);
            }else{
                this.drawUnselected(matrices, minecraftClient.getTextureManager());
            }
        }

        @Override
        public void onPress() {
            if(selected != null){
                client.openScreen(null);
                NbtCompound tagDATA = new NbtCompound();
                tagDATA.putString("chooseNation", selected.name());
                PacketClients.pcktClientValidateMenuData.send(MenuType.NATION_SELECTOR,tagDATA);
            }
        }

        private void drawUnselected(MatrixStack matrices, TextureManager textureManager) {
            textureManager.bindTexture(NationChooseMenuScreen.TEXTURE);
            matrices.push();
            matrices.translate((double)this.x, (double)this.y, 0.0D);
            drawTexture(matrices, 0, 0, 148, 21, 20, 20, 512, 512);
            matrices.pop();
        }

        private void drawSelected(MatrixStack matrices, TextureManager textureManager) {
            textureManager.bindTexture(NationChooseMenuScreen.TEXTURE);
            matrices.push();
            matrices.translate((double)this.x, (double)this.y, 0.0D);
            drawTexture(matrices, 0, 0, 148, 1, 20, 20, 512, 512);
            matrices.pop();
        }

    }

    class NationButtonWidget extends ButtonWidget {
        NationEnum nation;

        public NationButtonWidget(int x, int y, NationEnum nation) {
            super(x, y, 131, 32,null,null);
            this.nation = nation;
        }
  
        @Override
        public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            MinecraftClient minecraftClient = MinecraftClient.getInstance();
            if (selected == nation || this.isHovered()) nation.drawSelected(matrices, minecraftClient.getTextureManager(), this.x, this.y);
            else nation.drawUnselected(matrices, minecraftClient.getTextureManager(), this.x, this.y);
            if(this.isHovered()) renderTooltip(matrices, Text.of(nation.name.getString()), mouseX, mouseY);
        }

        @Override
        public void onPress() {
            selected = nation;
        }
    }

    enum NationEnum {
        Kawan(new TranslatableText("screen.edora.selectNation.nation.kawan"), 2, 199, 2, 167),
        Kallana(new TranslatableText("screen.edora.selectNation.nation.kallana"), 2, 263, 2, 231),
        Othala(new TranslatableText("screen.edora.selectNation.nation.othala"), 2, 327, 2, 295);

        final Text name;

        float unselectedU;
        float unselectedV;

        float selectedU;
        float selectedV;

        /**
         * Ajout d'un bouton de Nation
         * @param name Le nom de la Nation affiché lorsque le joueur place son curseur sur le bouton
         * @param unselectedU Coordonnée la plus à gauche du sprite
         * @param unselectedV Coordonnée la plus en haut du sprite
         * @param selectedU Coordonnée la plus à gauche du sprite
         * @param selectedV Coordonnée la plus en haut du sprite
         */
        NationEnum(Text name, float unselectedU, float unselectedV, float selectedU, float selectedV){
            this.name = name;

            this.unselectedU = unselectedU;
            this.unselectedV = unselectedV;

            this.selectedU = selectedU;
            this.selectedV = selectedV;
        }

        public void drawUnselected(MatrixStack matrices, TextureManager textureManager, int x, int y) {
            textureManager.bindTexture(NationChooseMenuScreen.TEXTURE);
            matrices.push();
            matrices.translate((double)x, (double)y, 0.0D);
            drawTexture(matrices, 0, 0, this.unselectedU, this.unselectedV, 131, 32, 512, 512);
            matrices.pop();
         }
   
         public void drawSelected(MatrixStack matrices, TextureManager textureManager, int x, int y) {
            textureManager.bindTexture(NationChooseMenuScreen.TEXTURE);
            matrices.push();
            matrices.translate((double)x, (double)y, 0.0D);
            drawTexture(matrices, 0, 0, this.selectedU, this.selectedV, 131, 32, 512, 512);
            matrices.pop();
         }

    }
}