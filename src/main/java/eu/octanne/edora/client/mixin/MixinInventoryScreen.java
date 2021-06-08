package eu.octanne.edora.client.mixin;

import java.util.List;

import com.mojang.blaze3d.systems.RenderSystem;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import eu.octanne.edora.client.EdoraClient;
import eu.octanne.edora.client.screen.menu.EdoraInventoryScreen;
import eu.octanne.edora.client.screen.menu.button.NationButton;
import eu.octanne.edora.packet.MenuType;
import eu.octanne.edora.packet.client.PacketClients;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookProvider;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

@Pseudo
@Mixin(InventoryScreen.class)
public class MixinInventoryScreen extends AbstractInventoryScreen<PlayerScreenHandler> implements RecipeBookProvider, EdoraInventoryScreen {

    @Shadow
    private static final Identifier RECIPE_BUTTON_TEXTURE = new Identifier("textures/gui/recipe_button.png");
    @Shadow
    private float mouseX;
    @Shadow
    private float mouseY;
    @Shadow
    private final RecipeBookWidget recipeBook = new RecipeBookWidget();

    @Shadow
    private boolean open;
    @Shadow
    private boolean narrow;
    @Shadow
    private boolean mouseDown;

    @Unique
    private boolean edoraMenuOpenState = false;
    @Unique
    private NationButton natButton = null;
    @Unique
    private NbtCompound edoraDATA = null;

    public MixinInventoryScreen(PlayerEntity player) {
        super(player.playerScreenHandler, player.getInventory(), new TranslatableText("container.crafting"));
        this.passEvents = true;
        this.titleX = 97;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if(EdoraClient.menuKeybind.matchesKey(keyCode, scanCode)) {
            if(!edoraMenuOpenState) PacketClients.pcktClientAskOpenMenu.send(MenuType.PERSONAL_MENU);
            else toggleEdoraMenu();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Inject(at = @At("RETURN"), method = "init()V")
    private void init(CallbackInfo info) {
        // Move RECIPE
        moveRecipeBook();
        // ADD Other Menu Button
        this.addDrawableChild(new TexturedButtonWidget(this.x + 146, this.y + 61, 20, 18, 223, 36, 18, EDORA_MAIN_MENU, 256, 256,
        buttonWidget -> {
            this.mouseDown = true;
            this.mouseDown = true;
        }));

        // EDORA MENU
        // ADD Menu Button
        TexturedButtonWidget menuButton = new TexturedButtonWidget(this.x + 100, this.y + 61, 20, 18, 223, 0, 18, EDORA_MAIN_MENU, 256, 256,
        buttonWidget -> {
            if(!edoraMenuOpenState) PacketClients.pcktClientAskOpenMenu.send(MenuType.PERSONAL_MENU);
            else toggleEdoraMenu();
            this.mouseDown = true;
        });
        this.addDrawableChild(menuButton);
        // Nation Button
        if(edoraMenuIsOpen()) {
            natButton = new NationButton(x - 39 , y + 19, NationButton.NationEnum.valueOf(edoraDATA.getString("nationName")));
            this.addDrawableChild(natButton);
        }
    }

    private void moveRecipeBook() {
        List<Drawable> drawables = ((ScreenAccessor) this).getDrawables();
        if(!drawables.isEmpty()){
            TexturedButtonWidget recipeBook = (TexturedButtonWidget)drawables.get(0);
            recipeBook.active = false;
            drawables.remove(0);
            this.addDrawableChild(new TexturedButtonWidget(this.x + 123, this.y + 61, 20, 18, 0, 0, 19, RECIPE_BUTTON_TEXTURE,
            buttonWidget -> toggleRecipeButton() ));
        }
    }

    @Override
    public void refreshRecipeBook() {
        this.recipeBook.refresh();
    }

    @Override
    public RecipeBookWidget getRecipeBookWidget() {
        return this.recipeBook;
    }

    @Override
    public void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
        this.client.getTextureManager().bindTexture(edoraMenuIsOpen() ? EDORA_MAIN_MENU : BACKGROUND_TEXTURE);
        int i = this.x;
        int j = this.y;

        this.drawTexture(matrices, edoraMenuOpenState ? i-47 : i, j, 0, 0, edoraMenuOpenState ? 223 : this.backgroundWidth, this.backgroundHeight);
        // DRAW SLOT OF SECOND ARMOR
        this.client.getTextureManager().bindTexture(EDORA_MAIN_MENU);
        this.drawTexture(matrices, x + 76, y + 7, 123, 7, 18, 54);

        // DRAW EDORA MENU DATA
        if(edoraMenuIsOpen()) {
            String townN = edoraDATA.getString("townName");
            Text noneT = new TranslatableText("screen.edora.any");
            Text townTitle = new TranslatableText("screen.edora.town");
            drawCenteredText(matrices, textRenderer, townN.equals("none") ? noneT : new LiteralText(townN), x - 19, y + 71, 125845);
            drawCenteredText(matrices, textRenderer, townTitle, x - 19, y + 61, 4416899);
            String guildeN = edoraDATA.getString("guildeName");
            Text guildeTitle = new TranslatableText("screen.edora.guilde");
            drawCenteredText(matrices, textRenderer, guildeN.equals("none") ? noneT : new LiteralText(townN), x - 19, y + 93, 16753920);
            drawCenteredText(matrices, textRenderer, guildeTitle, x - 19, y + 83, 4416899);
            Text nationTitle = new TranslatableText("screen.edora.nation");
            drawCenteredText(matrices, textRenderer, nationTitle, x - 19, y + 8, 4416899);

            Text moneyT = new LiteralText(edoraDATA.getInt("oannes")+"");
            drawCenteredText(matrices, textRenderer, moneyT, x - 19, y + 121, 16571451);
        }
        InventoryScreen.drawEntity(i + 51, j + 75, 30, (float)(i + 51) - this.mouseX, (float)(j + 75 - 50) - this.mouseY, this.client.player);
    }

    public void toggleRecipeButton() {
        if(!recipeBook.isOpen() && edoraMenuIsOpen()) closeEdoraMenu();
        this.recipeBook.reset();
        this.recipeBook.toggleOpen();
        int lastX = this.x;
        this.x = this.recipeBook.findLeftEdge(this.width, this.backgroundWidth);
        this.mouseDown = true;
        List<Drawable> drawables = ((ScreenAccessor) this).getDrawables();
        for (Drawable drawable : drawables) {
            ClickableWidget button = (ClickableWidget)drawable;
            button.x = button.x-lastX+this.x;
        }
    }

    @Override
    public void openEdoraMenu(NbtCompound data) {
        edoraDATA = data;
        edoraMenuOpenState = true;
        // Close Recipe if open
        if(recipeBook.isOpen()) toggleRecipeButton();
        if(natButton != null) {
            natButton.visible = true;
        } else {
            natButton = new NationButton(x - 39 , y + 19, NationButton.NationEnum.valueOf(data.getString("nationName")));
            this.addDrawableChild(natButton);
        }
    }

    @Override
    public void closeEdoraMenu() {
        edoraMenuOpenState = false;
        natButton.visible = false;
    }

    @Override
    public boolean toggleEdoraMenu() {
        if(edoraMenuIsOpen()) {
            closeEdoraMenu();
        }else {
            openEdoraMenu(edoraDATA);
        }
        return edoraMenuOpenState;
    }

    @Override
    public void updateData(NbtCompound dataTAG) {
        edoraDATA = dataTAG;
    }

    @Override
    public boolean edoraMenuIsOpen() {
        return edoraMenuOpenState;
    }
}