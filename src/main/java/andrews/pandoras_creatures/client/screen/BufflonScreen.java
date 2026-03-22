package andrews.pandoras_creatures.client.screen;

import andrews.pandoras_creatures.client.widget.bufflon.BufflonStateButton;
import andrews.pandoras_creatures.entities.BufflonEntity;
import andrews.pandoras_creatures.entities.bufflon.BufflonBackAttachmentType;
import andrews.pandoras_creatures.menu.BufflonMenuLayout;
import andrews.pandoras_creatures.menu.BufflonMenu;
import andrews.pandoras_creatures.registry.PCItems;
import andrews.pandoras_creatures.util.NetworkUtil;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BufflonScreen extends AbstractContainerScreen<BufflonMenu> {
    private static final ResourceLocation BUFFLON_GUI_TEXTURES = ResourceLocation.fromNamespaceAndPath(Reference.MODID, "textures/gui/menus/bufflon_menu.png");

    private int attachmentToRender = 1;

    private final BufflonEntity bufflonEntity;
    private float mousePosx;
    private float mousePosY;

    public BufflonScreen(BufflonMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.bufflonEntity = menu.getBufflonEntity();
        this.imageWidth = BufflonMenuLayout.IMAGE_WIDTH;
        this.imageHeight = BufflonMenuLayout.IMAGE_HEIGHT;
        this.titleLabelX = BufflonMenuLayout.TITLE_LABEL_X;
        this.titleLabelY = BufflonMenuLayout.TITLE_LABEL_Y;
        this.inventoryLabelX = BufflonMenuLayout.PLAYER_INVENTORY_LABEL_X;
        this.inventoryLabelY = BufflonMenuLayout.PLAYER_INVENTORY_LABEL_Y;
    }

    @Override
    protected void init() {
        super.init();

        this.addRenderableWidget(new BufflonStateButton(
                this.bufflonEntity,
                this.leftPos + BufflonMenuLayout.LEFT_BUTTON_X,
                this.topPos + BufflonMenuLayout.SIT_BUTTON_Y,
                0,
                "gui.button.pandoras_creatures.bufflon.sit",
                this.bufflonEntity::isSitting,
                bufflon -> {
                    if (!bufflon.isSitting()) {
                        NetworkUtil.requestBufflonSit(bufflon.getId(), true);
                    }
                    if (bufflon.isFollowingOwner()) {
                        NetworkUtil.requestBufflonFollow(bufflon.getId(), false);
                    }
                }
        ));

        this.addRenderableWidget(new BufflonStateButton(
                this.bufflonEntity,
                this.leftPos + BufflonMenuLayout.LEFT_BUTTON_X,
                this.topPos + BufflonMenuLayout.FOLLOW_BUTTON_Y,
                26,
                "gui.button.pandoras_creatures.bufflon.follow",
                this.bufflonEntity::isFollowingOwner,
                bufflon -> {
                    if (!bufflon.isFollowingOwner()) {
                        NetworkUtil.requestBufflonFollow(bufflon.getId(), true);
                    }
                    if (bufflon.isSitting()) {
                        NetworkUtil.requestBufflonSit(bufflon.getId(), false);
                    }
                }
        ));

        this.addRenderableWidget(new BufflonStateButton(
                this.bufflonEntity,
                this.leftPos + BufflonMenuLayout.LEFT_BUTTON_X,
                this.topPos + BufflonMenuLayout.MOVE_FREELY_BUTTON_Y,
                52,
                "gui.button.pandoras_creatures.bufflon.move_freely",
                () -> !this.bufflonEntity.isSitting() && !this.bufflonEntity.isFollowingOwner(),
                bufflon -> {
                    if (bufflon.isSitting()) {
                        NetworkUtil.requestBufflonSit(bufflon.getId(), false);
                    }
                    if (bufflon.isFollowingOwner()) {
                        NetworkUtil.requestBufflonFollow(bufflon.getId(), false);
                    }
                }
        ));

        this.addRenderableWidget(new BufflonStateButton(
                this.bufflonEntity,
                this.leftPos + BufflonMenuLayout.RIGHT_BUTTON_X,
                this.topPos + BufflonMenuLayout.COMBAT_BUTTON_Y,
                78,
                "gui.button.pandoras_creatures.bufflon.combat",
                this.bufflonEntity::isInCombatMode,
                bufflon -> {
                    if (!bufflon.isInCombatMode()) {
                        NetworkUtil.requestBufflonCombatMode(bufflon.getId(), true);
                    }
                }
        ));

        this.addRenderableWidget(new BufflonStateButton(
                this.bufflonEntity,
                this.leftPos + BufflonMenuLayout.RIGHT_BUTTON_X,
                this.topPos + BufflonMenuLayout.PEACEFUL_BUTTON_Y,
                104,
                "gui.button.pandoras_creatures.bufflon.peaceful",
                () -> !this.bufflonEntity.isInCombatMode(),
                bufflon -> {
                    if (bufflon.isInCombatMode()) {
                        NetworkUtil.requestBufflonCombatMode(bufflon.getId(), false);
                    }
                }
        ));
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        // The Bufflon Name
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 0x000000, false);
        // The Inventory Display Name
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 0x000000, false);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        guiGraphics.blit(BUFFLON_GUI_TEXTURES, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

        BufflonBackAttachmentType attachmentType = this.bufflonEntity.getBackAttachment();
        if (attachmentType.hasStorage()) {
            renderBufflonInventorySlots(guiGraphics, this.leftPos, this.topPos, attachmentType.getStorageRows());
        }

        // The Bufflon Entity inside the Menu
        InventoryScreen.renderEntityInInventoryFollowsMouse(
                guiGraphics,
                this.leftPos + BufflonMenuLayout.ENTITY_RENDER_X,
                this.topPos + BufflonMenuLayout.ENTITY_RENDER_MOUSE_Y,
                this.leftPos + BufflonMenuLayout.ENTITY_RENDER_X,
                this.topPos + BufflonMenuLayout.ENTITY_RENDER_Y,
                BufflonMenuLayout.ENTITY_RENDER_SIZE,
                BufflonMenuLayout.ENTITY_RENDER_SCALE,
                this.mousePosx,
                this.mousePosY,
                this.bufflonEntity
        );

        // The Saddle slot previews
        if (!bufflonEntity.isSaddled()) {
            guiGraphics.renderFakeItem(new ItemStack(PCItems.BUFFLON_SADDLE.get()),
                    this.leftPos + BufflonMenuLayout.SADDLE_SLOT_X,
                    this.topPos + BufflonMenuLayout.SADDLE_SLOT_Y);
        }

        if (!bufflonEntity.hasBackAttachment()) {
            switch (attachmentToRender) {
                case 1:
                    guiGraphics.renderFakeItem(new ItemStack(PCItems.BUFFLON_PLAYER_SEATS.get()),
                            this.leftPos + BufflonMenuLayout.BACK_ATTACHMENT_SLOT_X,
                            this.topPos + BufflonMenuLayout.BACK_ATTACHMENT_SLOT_Y);
                    break;
                case 2:
                    guiGraphics.renderFakeItem(new ItemStack(PCItems.BUFFLON_SMALL_STORAGE.get()),
                            this.leftPos + BufflonMenuLayout.BACK_ATTACHMENT_SLOT_X,
                            this.topPos + BufflonMenuLayout.BACK_ATTACHMENT_SLOT_Y);
                    break;
                case 3:
                    guiGraphics.renderFakeItem(new ItemStack(PCItems.BUFFLON_LARGE_STORAGE.get()),
                            this.leftPos + BufflonMenuLayout.BACK_ATTACHMENT_SLOT_X,
                            this.topPos + BufflonMenuLayout.BACK_ATTACHMENT_SLOT_Y);
                    break;
            }
        }
    }

    @Override
    public void containerTick() {
        super.containerTick();
        if ((bufflonEntity.tickCount % 60) == 0) {
            // Updates the attachment type that should be rendered every 60 ticks / 3 seconds
            if (attachmentToRender < 3) {
                attachmentToRender++;
            } else {
                attachmentToRender = 1;
            }
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.mousePosx = (float) mouseX;
        this.mousePosY = (float) mouseY;
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    /**
     * Renders the Inventory Slots of this Bufflon Entity
     * @param posX The x position this is relative to
     * @param posY The y position this is relative to
     * @param rows The amount of rows that should be rendered
     */
    private void renderBufflonInventorySlots(GuiGraphics guiGraphics, int posX, int posY, int rows) {
        for (int i = 0; i < rows; i++) {
            guiGraphics.blit(
                    BUFFLON_GUI_TEXTURES,
                    posX + BufflonMenuLayout.STORAGE_BACKGROUND_X,
                    posY + BufflonMenuLayout.STORAGE_BACKGROUND_Y + (i * BufflonMenuLayout.SLOT_SPACING),
                    0,
                    238,
                    162,
                    18
            );
        }
    }
}
