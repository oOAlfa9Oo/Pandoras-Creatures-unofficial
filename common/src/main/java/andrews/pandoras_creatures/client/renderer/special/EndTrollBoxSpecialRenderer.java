package andrews.pandoras_creatures.client.renderer.special;

import andrews.pandoras_creatures.client.model.base.PCModelLayers;
import andrews.pandoras_creatures.client.model.tile.EndTrollBoxModel;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxPalette;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * 1.21.4 elimino BlockEntityWithoutLevelRenderer / RenderShape.ENTITYBLOCK_ANIMATED (ver
 * RenderShape.java, solo quedan INVISIBLE/MODEL) y los reemplazo por este sistema de
 * SpecialModelRenderer, dirigido por un item model JSON con "type": "minecraft:special"
 * (ver SpecialModelWrapper.java / SpecialModelRenderers.java, verificado contra la fuente real
 * de 1.21.4). Reemplaza a PCItemRenderer.
 */
public final class EndTrollBoxSpecialRenderer implements SpecialModelRenderer<Void> {
    private final EndTrollBoxModel model;
    private final ResourceLocation texture;

    private EndTrollBoxSpecialRenderer(EndTrollBoxModel model, ResourceLocation texture) {
        this.model = model;
        this.texture = texture;
    }

    @Nullable
    @Override
    public Void extractArgument(ItemStack stack) {
        return null;
    }

    @Override
    public void render(@Nullable Void argument, ItemDisplayContext displayContext, PoseStack poseStack,
                        MultiBufferSource buffer, int packedLight, int packedOverlay, boolean hasFoil) {
        poseStack.pushPose();

        var vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(this.texture));

        poseStack.translate(0.5D, 1.5D, 0.5D);
        poseStack.scale(1.0F, -1.0F, -1.0F);

        this.model.base.render(poseStack, vertexConsumer, packedLight, packedOverlay);

        this.model.bottom_front_left.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        this.model.bottom_front_right.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        this.model.bottom_back_left.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        this.model.bottom_back_right.render(poseStack, vertexConsumer, packedLight, packedOverlay);

        this.model.decoration_front_left.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        this.model.decoration_front_right.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        this.model.decoration_back_left.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        this.model.decoration_back_right.render(poseStack, vertexConsumer, packedLight, packedOverlay);

        this.model.lid_front.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        this.model.lid_back.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        this.model.lid_left.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        this.model.lid_right.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        this.model.lid_top_left.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        this.model.lid_top_right.render(poseStack, vertexConsumer, packedLight, packedOverlay);

        this.model.top_front_left.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        this.model.top_front_left_1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        this.model.top_back_left.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        this.model.top_back_right.render(poseStack, vertexConsumer, packedLight, packedOverlay);

        poseStack.popPose();
    }

    public static final class Unbaked implements SpecialModelRenderer.Unbaked {
        public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath("pandoras_creatures", "end_troll_box");
        public static final MapCodec<Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        DyeColor.CODEC.optionalFieldOf("color").forGetter(Unbaked::color)
                ).apply(instance, Unbaked::new));

        private final Optional<DyeColor> color;

        public Unbaked(Optional<DyeColor> color) {
            this.color = color;
        }

        private Optional<DyeColor> color() {
            return this.color;
        }

        @Override
        public MapCodec<Unbaked> type() {
            return MAP_CODEC;
        }

        @Override
        public SpecialModelRenderer<?> bake(EntityModelSet modelSet) {
            EndTrollBoxModel model = new EndTrollBoxModel(modelSet.bakeLayer(PCModelLayers.END_TROLL_BOX));
            ResourceLocation texture = PCEndTrollBoxPalette.textureId(this.color.orElse(null));
            return new EndTrollBoxSpecialRenderer(model, texture);
        }
    }
}
