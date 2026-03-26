package andrews.pandoras_creatures.datagen.model;

import andrews.pandoras_creatures.registry.PCBlocks;
import andrews.pandoras_creatures.registry.PCItems;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public final class PCItemModelDataProvider extends ItemModelProvider {
    public PCItemModelDataProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
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
        PCItems.getSpawnEggs().forEach(spawnEgg -> spawnEggItem(spawnEgg.get()));
    }

    private void registerSimpleItems() {
        basicItem(PCItems.ACIDIC_ARCHVINE_TONGUE.get());
        basicItem(PCItems.BUFFLON_BEEF.get());
        basicItem(PCItems.BUFFLON_BEEF_COOKED.get());
        basicItem(PCItems.BUFFLON_HIDE.get());
        basicItem(PCItems.CRAB_BUCKET.get());
        basicItem(PCItems.END_TROLL_SKIN.get());
        basicItem(PCItems.HERB_BUNDLE.get());
        basicItem(PCItems.PLANT_HAT.get());
        basicItem(PCItems.SEAHORSE_BUCKET.get());
        generatedItem(PCBlocks.PANDORIC_SHARD.get().asItem(), modLoc("item/crystal"));
    }

    private void registerCustomDisplayItems() {
        basicItem(PCItems.CRAB_MEAT.get())
                .transforms()
                .transform(ItemDisplayContext.GUI)
                .scale(0.8F, 0.8F, 0.0001F)
                .end()
                .end();

        basicItem(PCItems.CRAB_MEAT_COOKED.get())
                .transforms()
                .transform(ItemDisplayContext.GUI)
                .scale(0.8F, 0.8F, 0.0001F)
                .end()
                .end();

        registerSeahorseItem(PCItems.SEAHORSE.get());
        registerSeahorseItem(PCItems.SEAHORSE_COOKED.get());
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
        simpleBlockItem(PCBlocks.ARACHNON_CRYSTAL.get());
        generatedItem(PCBlocks.HORSETAIL.get().asItem(), modLoc("block/horsetail"));
        generatedItem(PCBlocks.DHANIA.get().asItem(), modLoc("block/dhania"));
        generatedItem(PCBlocks.HILL_BLOOM.get().asItem(), modLoc("block/hill_bloom"));
    }

    private void registerEndTrollBoxItems() {
        for (Item item : PCBlocks.getEndTrollBoxItems()) {
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
