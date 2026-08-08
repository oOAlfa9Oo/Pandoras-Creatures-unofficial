package andrews.pandoras_creatures.forge.datagen.model;

import andrews.pandoras_creatures.forge.registry.PCForgeBlocks;
import andrews.pandoras_creatures.forge.registry.PCForgeItems;
import andrews.pandoras_creatures.registry.block.PCBlockIds;
import andrews.pandoras_creatures.registry.item.PCItemIds;
import andrews.pandoras_creatures.registry.item.PCSpawnEggPalette;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * Copia forge-especifica de andrews.pandoras_creatures.datagen.model.PCItemModelDataProvider
 * (neoforge). Ver PCForgeBlockStateModelDataProvider para la razon de por que no vive en common.
 * Forge's ItemModelProvider no trae el helper spawnEggItem(Item) que si tiene neoforge; se
 * inlinea aca su misma logica (parent "item/template_spawn_egg", verificado contra el fuente
 * real de neoforge-21.1.77-sources.jar).
 */
public final class PCForgeItemModelDataProvider extends ItemModelProvider {
    public PCForgeItemModelDataProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Reference.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        registerSpawnEggs();
        registerSimpleItems();
        registerCustomDisplayItems();
        registerBlockBackedItems();
        registerEndTrollBoxItems();
    }

    private void registerSpawnEggs() {
        PCSpawnEggPalette.values().forEach(palette -> spawnEggItem(PCForgeItems.getItem(palette.itemName())));
    }

    private ItemModelBuilder spawnEggItem(Item item) {
        return getBuilder(path(item))
                .parent(new ModelFile.UncheckedModelFile("item/template_spawn_egg"));
    }

    private void registerSimpleItems() {
        basicItem(PCForgeItems.getItem(PCItemIds.ACIDIC_ARCHVINE_TONGUE));
        basicItem(PCForgeItems.getItem(PCItemIds.BUFFLON_BEEF));
        basicItem(PCForgeItems.getItem(PCItemIds.BUFFLON_BEEF_COOKED));
        basicItem(PCForgeItems.getItem(PCItemIds.BUFFLON_HIDE));
        basicItem(PCForgeItems.getItem(PCItemIds.CRAB_BUCKET));
        basicItem(PCForgeItems.getItem(PCItemIds.END_TROLL_SKIN));
        basicItem(PCForgeItems.getItem(PCItemIds.HERB_BUNDLE));
        basicItem(PCForgeItems.getItem(PCItemIds.PLANT_HAT));
        basicItem(PCForgeItems.getItem(PCItemIds.SEAHORSE_BUCKET));
        generatedItem(PCForgeBlocks.getSimpleBlock(PCBlockIds.PANDORIC_SHARD).asItem(), modLoc("item/crystal"));
    }

    private void registerCustomDisplayItems() {
        basicItem(PCForgeItems.getItem(PCItemIds.CRAB_MEAT))
                .transforms()
                .transform(ItemDisplayContext.GUI)
                .scale(0.8F, 0.8F, 0.0001F)
                .end()
                .end();

        basicItem(PCForgeItems.getItem(PCItemIds.CRAB_MEAT_COOKED))
                .transforms()
                .transform(ItemDisplayContext.GUI)
                .scale(0.8F, 0.8F, 0.0001F)
                .end()
                .end();

        registerSeahorseItem(PCForgeItems.getItem(PCItemIds.SEAHORSE));
        registerSeahorseItem(PCForgeItems.getItem(PCItemIds.SEAHORSE_COOKED));
    }

    private void registerSeahorseItem(Item item) {
        basicItem(item)
                .transforms()
                .transform(ItemDisplayContext.GUI)
                .scale(1.2F, 1.2F, 1.2F)
                .end()
                .transform(ItemDisplayContext.GROUND)
                .translation(0.0F, 2.0F, 0.0F)
                .scale(0.7F, 0.7F, 0.7F)
                .end()
                .end();
    }

    private void registerBlockBackedItems() {
        simpleBlockItem(PCForgeBlocks.getSimpleBlock(PCBlockIds.ARACHNON_CRYSTAL));
        generatedItem(PCForgeBlocks.getSimpleBlock(PCBlockIds.HORSETAIL).asItem(), modLoc("block/horsetail"));
        generatedItem(PCForgeBlocks.getSimpleBlock(PCBlockIds.DHANIA).asItem(), modLoc("block/dhania"));
        generatedItem(PCForgeBlocks.getSimpleBlock(PCBlockIds.HILL_BLOOM).asItem(), modLoc("block/hill_bloom"));
    }

    private ItemModelBuilder simpleBlockItem(net.minecraft.world.level.block.Block block) {
        ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(block);
        return withExistingParent(blockId.toString(), ResourceLocation.fromNamespaceAndPath(blockId.getNamespace(), "block/" + blockId.getPath()));
    }

    private void registerEndTrollBoxItems() {
        for (Item item : PCForgeItems.getEndTrollBoxItems()) {
            endTrollBoxItem(item);
        }
    }

    private void endTrollBoxItem(Item item) {
        getBuilder(path(item))
                .parent(new ModelFile.UncheckedModelFile("builtin/entity"))
                .transforms()
                .transform(ItemDisplayContext.GUI)
                .rotation(30.0F, 45.0F, 0.0F)
                .scale(0.625F, 0.625F, 0.625F)
                .end()
                .transform(ItemDisplayContext.GROUND)
                .translation(0.0F, 3.0F, 0.0F)
                .scale(0.25F, 0.25F, 0.25F)
                .end()
                .transform(ItemDisplayContext.HEAD)
                .rotation(0.0F, 180.0F, 0.0F)
                .end()
                .transform(ItemDisplayContext.FIXED)
                .rotation(0.0F, 180.0F, 0.0F)
                .scale(0.5F, 0.5F, 0.5F)
                .end()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
                .rotation(75.0F, 315.0F, 0.0F)
                .translation(0.0F, 2.5F, 0.0F)
                .scale(0.375F, 0.375F, 0.375F)
                .end()
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
                .rotation(0.0F, 315.0F, 0.0F)
                .scale(0.4F, 0.4F, 0.4F)
                .end()
                .end();
    }

    private ItemModelBuilder generatedItem(Item item, ResourceLocation texture) {
        return getBuilder(path(item))
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", texture);
    }

    private static String path(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).getPath();
    }
}
