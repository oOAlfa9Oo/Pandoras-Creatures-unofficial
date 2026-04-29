package andrews.pandoras_creatures.client.screen;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.client.widget.bufflon.BufflonStateButton;
import andrews.pandoras_creatures.entities.bufflon.BufflonAccess;
import andrews.pandoras_creatures.entities.bufflon.BufflonBackAttachmentType;
import andrews.pandoras_creatures.lang.PCLanguageKeys;
import andrews.pandoras_creatures.menu.BufflonMenu;
import andrews.pandoras_creatures.menu.BufflonMenuLayout;
import andrews.pandoras_creatures.registry.item.PCItemIds;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class BufflonScreen extends AbstractContainerScreen<BufflonMenu> {
    private static final ResourceLocation BUFFLON_GUI_TEXTURES = new ResourceLocation(Reference.MODID, "textures/gui/menus/bufflon_menu.png");

    private final BufflonAccess bufflon;
    private final LivingEntity bufflonEntity;
    private int attachmentToRender = 1;
    private float mousePosx;
    private float mousePosY;

    public BufflonScreen(BufflonMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.bufflon = menu.getBufflon();
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

        this.addRenderableWidget(new BufflonStateButton(this.bufflon, this.leftPos + BufflonMenuLayout.LEFT_BUTTON_X,
                this.topPos + BufflonMenuLayout.SIT_BUTTON_Y, 0, PCLanguageKeys.guiButton("bufflon.sit"),
                this.bufflon::isBufflonSitting, currentBufflon -> {
                    if (!currentBufflon.isBufflonSitting()) {
                        PandorasCreaturesCommon.platform().network().requestBufflonSit(currentBufflon.getBufflonId(), true);
                    }
                    if (currentBufflon.isBufflonFollowingOwner()) {
                        PandorasCreaturesCommon.platform().network().requestBufflonFollow(currentBufflon.getBufflonId(), false);
                    }
                }));

        this.addRenderableWidget(new BufflonStateButton(this.bufflon, this.leftPos + BufflonMenuLayout.LEFT_BUTTON_X,
                this.topPos + BufflonMenuLayout.FOLLOW_BUTTON_Y, 26, PCLanguageKeys.guiButton("bufflon.follow"),
                this.bufflon::isBufflonFollowingOwner, currentBufflon -> {
                    if (!currentBufflon.isBufflonFollowingOwner()) {
                        PandorasCreaturesCommon.platform().network().requestBufflonFollow(currentBufflon.getBufflonId(), true);
                    }
                    if (currentBufflon.isBufflonSitting()) {
                        PandorasCreaturesCommon.platform().network().requestBufflonSit(currentBufflon.getBufflonId(), false);
                    }
                }));

        this.addRenderableWidget(new BufflonStateButton(this.bufflon, this.leftPos + BufflonMenuLayout.LEFT_BUTTON_X,
                this.topPos + BufflonMenuLayout.MOVE_FREELY_BUTTON_Y, 52, PCLanguageKeys.guiButton("bufflon.move_freely"),
                () -> !this.bufflon.isBufflonSitting() && !this.bufflon.isBufflonFollowingOwner(), currentBufflon -> {
                    if (currentBufflon.isBufflonSitting()) {
                        PandorasCreaturesCommon.platform().network().requestBufflonSit(currentBufflon.getBufflonId(), false);
                    }
                    if (currentBufflon.isBufflonFollowingOwner()) {
                        PandorasCreaturesCommon.platform().network().requestBufflonFollow(currentBufflon.getBufflonId(), false);
                    }
                }));

        this.addRenderableWidget(new BufflonStateButton(this.bufflon, this.leftPos + BufflonMenuLayout.RIGHT_BUTTON_X,
                this.topPos + BufflonMenuLayout.COMBAT_BUTTON_Y, 78, PCLanguageKeys.guiButton("bufflon.combat"),
                this.bufflon::isBufflonInCombatMode, currentBufflon -> {
                    if (!currentBufflon.isBufflonInCombatMode()) {
                        PandorasCreaturesCommon.platform().network().requestBufflonCombatMode(currentBufflon.getBufflonId(), true);
                    }
                }));

        this.addRenderableWidget(new BufflonStateButton(this.bufflon, this.leftPos + BufflonMenuLayout.RIGHT_BUTTON_X,
                this.topPos + BufflonMenuLayout.PEACEFUL_BUTTON_Y, 104, PCLanguageKeys.guiButton("bufflon.peaceful"),
                () -> !this.bufflon.isBufflonInCombatMode(), currentBufflon -> {
                    if (currentBufflon.isBufflonInCombatMode()) {
                        PandorasCreaturesCommon.platform().network().requestBufflonCombatMode(currentBufflon.getBufflonId(), false);
                    }
                }));
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 0x000000, false);
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 0x000000, false);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        guiGraphics.blit(BUFFLON_GUI_TEXTURES, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

        BufflonBackAttachmentType attachmentType = this.bufflon.getBufflonBackAttachment();
        if (attachmentType.hasStorage()) {
            renderBufflonInventorySlots(guiGraphics, this.leftPos, this.topPos, attachmentType.getStorageRows());
        }

        InventoryScreen.renderEntityInInventoryFollowsMouse(guiGraphics, this.leftPos + BufflonMenuLayout.ENTITY_RENDER_X,
                this.topPos + BufflonMenuLayout.ENTITY_RENDER_Y, BufflonMenuLayout.ENTITY_RENDER_SIZE,
                this.mousePosx, this.mousePosY, this.bufflonEntity);

        if (!bufflon.isBufflonSaddled()) {
            guiGraphics.renderFakeItem(new ItemStack(PandorasCreaturesCommon.platform().registry().item(PCItemIds.BUFFLON_SADDLE)),
                    this.leftPos + BufflonMenuLayout.SADDLE_SLOT_X, this.topPos + BufflonMenuLayout.SADDLE_SLOT_Y);
        }

        if (!bufflon.hasBufflonBackAttachment()) {
            String itemId = switch (attachmentToRender) {
                case 1 -> PCItemIds.BUFFLON_PLAYER_SEATS;
                case 2 -> PCItemIds.BUFFLON_SMALL_STORAGE;
                default -> PCItemIds.BUFFLON_LARGE_STORAGE;
            };
            guiGraphics.renderFakeItem(new ItemStack(PandorasCreaturesCommon.platform().registry().item(itemId)),
                    this.leftPos + BufflonMenuLayout.BACK_ATTACHMENT_SLOT_X, this.topPos + BufflonMenuLayout.BACK_ATTACHMENT_SLOT_Y);
        }
    }

    @Override
    public void containerTick() {
        super.containerTick();
        if ((bufflon.getBufflonTickCount() % 60) == 0) {
            attachmentToRender = attachmentToRender < 3 ? attachmentToRender + 1 : 1;
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.mousePosx = (float) mouseX;
        this.mousePosY = (float) mouseY;
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    private void renderBufflonInventorySlots(GuiGraphics guiGraphics, int posX, int posY, int rows) {
        for (int i = 0; i < rows; i++) {
            guiGraphics.blit(BUFFLON_GUI_TEXTURES, posX + BufflonMenuLayout.STORAGE_BACKGROUND_X,
                    posY + BufflonMenuLayout.STORAGE_BACKGROUND_Y + (i * BufflonMenuLayout.SLOT_SPACING), 0, 238, 162, 18);
        }
    }
}

