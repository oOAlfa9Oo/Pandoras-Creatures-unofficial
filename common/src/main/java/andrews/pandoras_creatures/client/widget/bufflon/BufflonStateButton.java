package andrews.pandoras_creatures.client.widget.bufflon;

import andrews.pandoras_creatures.entities.bufflon.BufflonAccess;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class BufflonStateButton extends Button {
    private static final ResourceLocation TEXTURE = Reference.id("textures/gui/buttons/bufflon_menu_buttons.png");
    private static final int BUTTON_WIDTH = 26;
    private static final int BUTTON_HEIGHT = 26;
    private static final int TEXTURE_U_SELECTED = 26;
    private static final int TEXTURE_U_DISABLED = 52;

    private final BufflonAccess bufflon;
    private final int textureV;
    private final BooleanSupplier selectedState;

    public BufflonStateButton(BufflonAccess bufflon, int x, int y, int textureV, String tooltipKey,
                              BooleanSupplier selectedState, Consumer<BufflonAccess> onPress) {
        super(x, y, BUTTON_WIDTH, BUTTON_HEIGHT, Component.empty(), button -> onPress.accept(bufflon), DEFAULT_NARRATION);
        this.bufflon = bufflon;
        this.textureV = textureV;
        this.selectedState = selectedState;
        this.setTooltip(Tooltip.create(Component.translatable(tooltipKey)));
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        Player localPlayer = Minecraft.getInstance().player;
        this.active = localPlayer != null && this.bufflon.isBufflonOwnedBy(localPlayer);

        int textureU = 0;
        if (!this.active) {
            textureU = TEXTURE_U_DISABLED;
        } else if (this.selectedState.getAsBoolean()) {
            textureU = TEXTURE_U_SELECTED;
        }

        guiGraphics.blit(TEXTURE, this.getX(), this.getY(), textureU, this.textureV, this.width, this.height);
    }
}

