package andrews.pandoras_creatures.client.screen;

import andrews.pandoras_creatures.menu.EndTrollBoxMenu;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class EndTrollBoxScreen extends AbstractContainerScreen<EndTrollBoxMenu> {
    private static final Identifier GUI_TEXTURE = Identifier.fromNamespaceAndPath(Reference.MODID, "textures/gui/containers/end_troll_box.png");

    public EndTrollBoxScreen(EndTrollBoxMenu menu, Inventory playerInv, Component title) {
        super(menu, playerInv, title, 176, 220);
        this.inventoryLabelY = this.imageHeight - 93;
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY) {
        guiGraphics.text(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
        guiGraphics.text(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 4210752, false);
    }

    @Override
    public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x, y, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);
        super.extractContents(guiGraphics, mouseX, mouseY, partialTicks);
    }
}
