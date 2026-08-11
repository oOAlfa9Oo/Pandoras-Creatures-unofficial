package andrews.pandoras_creatures.datagen.model;

import andrews.pandoras_creatures.client.renderer.special.EndTrollBoxSpecialRenderer;
import andrews.pandoras_creatures.registry.PCBlocks;
import andrews.pandoras_creatures.registry.PCItems;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxBootstrap;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxPalette;
import andrews.pandoras_creatures.registry.item.PCSpawnEggPalette;
import andrews.pandoras_creatures.util.Reference;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Optional;
import java.util.stream.Stream;

// ADR-0006 / 1.21.4: contraparte de PCBlockStateModelDataProvider, ver el comentario de esa
// clase. Este provider ademas escribe assets/pandoras_creatures/items/<id>.json (formato nuevo,
// primario desde 1.21.4) via itemModelOutput; los items con SpecialModelRenderer (End Troll Box)
// usan ItemModelUtils.specialModel apuntando al SpecialModelRenderer.Unbaked registrado en
// PCSpecialModelRendererRegistrar.
public final class PCItemModelDataProvider extends ModelProvider {
    public PCItemModelDataProvider(PackOutput output) {
        super(output, Reference.MODID);
    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return Stream.empty();
    }

    @Override
    public String getName() {
        return super.getName() + " (items)";
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        registerSpawnEggs(itemModels);
        registerSimpleItems(itemModels);
        registerCustomDisplayItems(itemModels);
        registerBlockBackedItems(itemModels);
        registerExistingModelItems(itemModels);
        registerEndTrollBoxItems(itemModels);
        // ARACHNON_CRYSTAL no se registra: al ser un BlockItem simple sin entrada explicita,
        // ModelProvider.ItemInfoCollector genera automaticamente un item model cuyo parent es el
        // modelo del bloque (pandoras_creatures:block/arachnon_crystal), igual que antes.
    }

    private void registerSpawnEggs(ItemModelGenerators itemModels) {
        // 1.21.5 elimino el modelo compartido item/template_spawn_egg + el tinting por
        // ItemModelGenerators#generateSpawnEgg (removido de la API): vanilla ahora usa una
        // textura plana propia por huevo (item/<id>_spawn_egg.png), sin tints en el modelo.
        // Sin arte propio por criatura, se registra como item plano igual que cualquier otro
        // (mismo patron que Items.PIG_SPAWN_EGG en 1.21.5); PCSpawnEggPalette sigue existiendo
        // por si el color se necesita en otro lado, pero el datagen de items ya no lo consume.
        for (PCSpawnEggPalette palette : PCSpawnEggPalette.values()) {
            Item item = itemByPath(palette.itemName());
            itemModels.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
        }
    }

    private void registerSimpleItems(ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(PCItems.ACIDIC_ARCHVINE_TONGUE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(PCItems.BUFFLON_BEEF.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(PCItems.BUFFLON_BEEF_COOKED.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(PCItems.BUFFLON_HIDE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(PCItems.CRAB_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(PCItems.END_TROLL_SKIN.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(PCItems.HERB_BUNDLE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(PCItems.PLANT_HAT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(PCItems.SEAHORSE_BUCKET.get(), ModelTemplates.FLAT_ITEM);
    }

    private void registerCustomDisplayItems(ItemModelGenerators itemModels) {
        registerScaledItem(itemModels, PCItems.CRAB_MEAT.get(), new float[]{0.8F, 0.8F, 0.0001F});
        registerScaledItem(itemModels, PCItems.CRAB_MEAT_COOKED.get(), new float[]{0.8F, 0.8F, 0.0001F});
        registerSeahorseItem(itemModels, PCItems.SEAHORSE.get());
        registerSeahorseItem(itemModels, PCItems.SEAHORSE_COOKED.get());
    }

    private void registerScaledItem(ItemModelGenerators itemModels, Item item, float[] guiScale) {
        ResourceLocation modelLoc = ModelLocationUtils.getModelLocation(item);
        ResourceLocation texture = TextureMapping.getItemTexture(item);
        itemModels.modelOutput.accept(modelLoc, () -> {
            JsonObject json = generatedItemJson(texture);
            JsonObject display = new JsonObject();
            display.add("gui", displayEntry(null, null, guiScale));
            json.add("display", display);
            return json;
        });
        itemModels.itemModelOutput.accept(item, ItemModelUtils.plainModel(modelLoc));
    }

    private void registerSeahorseItem(ItemModelGenerators itemModels, Item item) {
        ResourceLocation modelLoc = ModelLocationUtils.getModelLocation(item);
        ResourceLocation texture = TextureMapping.getItemTexture(item);
        itemModels.modelOutput.accept(modelLoc, () -> {
            JsonObject json = generatedItemJson(texture);
            JsonObject display = new JsonObject();
            display.add("gui", displayEntry(null, null, new float[]{1.2F, 1.2F, 1.2F}));
            display.add("ground", displayEntry(null, new float[]{0.0F, 2.0F, 0.0F}, new float[]{0.7F, 0.7F, 0.7F}));
            json.add("display", display);
            return json;
        });
        itemModels.itemModelOutput.accept(item, ItemModelUtils.plainModel(modelLoc));
    }

    private void registerBlockBackedItems(ItemModelGenerators itemModels) {
        registerGeneratedItem(itemModels, PCBlocks.HORSETAIL.get().asItem(), modLoc("block/horsetail"));
        registerGeneratedItem(itemModels, PCBlocks.DHANIA.get().asItem(), modLoc("block/dhania"));
        registerGeneratedItem(itemModels, PCBlocks.HILL_BLOOM.get().asItem(), modLoc("block/hill_bloom"));
        registerGeneratedItem(itemModels, PCBlocks.PANDORIC_SHARD.get().asItem(), modLoc("item/crystal"));
    }

    private void registerGeneratedItem(ItemModelGenerators itemModels, Item item, ResourceLocation texture) {
        ResourceLocation modelLoc = ModelTemplates.FLAT_ITEM.create(
                ModelLocationUtils.getModelLocation(item), TextureMapping.layer0(texture), itemModels.modelOutput);
        itemModels.itemModelOutput.accept(item, ItemModelUtils.plainModel(modelLoc));
    }

    private void registerExistingModelItems(ItemModelGenerators itemModels) {
        // Estos 5 items ya tienen su geometria hecha a mano en
        // common/src/main/resources/assets/pandoras_creatures/models/item/*.json (sin "parent",
        // con "elements" propios). Antes de 1.21.4 alcanzaba con ese archivo; ahora ademas hace
        // falta el wrapper items/<id>.json que apunte a ese mismo modelo.
        registerExistingModelItem(itemModels, PCItems.ARACHNON_HAMMER.get());
        registerExistingModelItem(itemModels, PCItems.BUFFLON_SADDLE.get());
        registerExistingModelItem(itemModels, PCItems.BUFFLON_PLAYER_SEATS.get());
        registerExistingModelItem(itemModels, PCItems.BUFFLON_SMALL_STORAGE.get());
        registerExistingModelItem(itemModels, PCItems.BUFFLON_LARGE_STORAGE.get());
    }

    private void registerExistingModelItem(ItemModelGenerators itemModels, Item item) {
        itemModels.itemModelOutput.accept(item, ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(item)));
    }

    private void registerEndTrollBoxItems(ItemModelGenerators itemModels) {
        registerEndTrollBoxItem(itemModels, null);
        for (DyeColor color : PCEndTrollBoxPalette.orderedColors()) {
            registerEndTrollBoxItem(itemModels, color);
        }
    }

    private void registerEndTrollBoxItem(ItemModelGenerators itemModels, DyeColor color) {
        Item item = itemByPath(PCEndTrollBoxBootstrap.blockId(color));
        ResourceLocation baseLoc = ModelLocationUtils.getModelLocation(item);
        itemModels.modelOutput.accept(baseLoc, PCItemModelDataProvider::endTrollBoxBaseModel);
        itemModels.itemModelOutput.accept(item,
                ItemModelUtils.specialModel(baseLoc, new EndTrollBoxSpecialRenderer.Unbaked(Optional.ofNullable(color))));
    }

    private static JsonObject endTrollBoxBaseModel() {
        JsonObject json = new JsonObject();
        json.addProperty("parent", "minecraft:builtin/entity");
        JsonObject display = new JsonObject();
        display.add("gui", displayEntry(new float[]{30.0F, 45.0F, 0.0F}, null, new float[]{0.625F, 0.625F, 0.625F}));
        display.add("ground", displayEntry(null, new float[]{0.0F, 3.0F, 0.0F}, new float[]{0.25F, 0.25F, 0.25F}));
        display.add("head", displayEntry(new float[]{0.0F, 180.0F, 0.0F}, null, null));
        display.add("fixed", displayEntry(new float[]{0.0F, 180.0F, 0.0F}, null, new float[]{0.5F, 0.5F, 0.5F}));
        display.add("thirdperson_righthand", displayEntry(new float[]{75.0F, 315.0F, 0.0F}, new float[]{0.0F, 2.5F, 0.0F}, new float[]{0.375F, 0.375F, 0.375F}));
        display.add("firstperson_righthand", displayEntry(new float[]{0.0F, 315.0F, 0.0F}, null, new float[]{0.4F, 0.4F, 0.4F}));
        json.add("display", display);
        return json;
    }

    private static JsonObject generatedItemJson(ResourceLocation texture) {
        JsonObject json = new JsonObject();
        json.addProperty("parent", "minecraft:item/generated");
        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", texture.toString());
        json.add("textures", textures);
        return json;
    }

    private static JsonObject displayEntry(float[] rotation, float[] translation, float[] scale) {
        JsonObject entry = new JsonObject();
        if (rotation != null) {
            entry.add("rotation", jsonArray(rotation));
        }
        if (translation != null) {
            entry.add("translation", jsonArray(translation));
        }
        if (scale != null) {
            entry.add("scale", jsonArray(scale));
        }
        return entry;
    }

    private static JsonArray jsonArray(float[] values) {
        JsonArray array = new JsonArray();
        for (float value : values) {
            array.add(value);
        }
        return array;
    }

    private static ResourceLocation modLoc(String path) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MODID, path);
    }

    private static Item itemByPath(String path) {
        Item item = BuiltInRegistries.ITEM.getValue(ResourceLocation.fromNamespaceAndPath(Reference.MODID, path));
        if (item == null) {
            throw new IllegalStateException("Unknown pandoras_creatures item: " + path);
        }
        return item;
    }
}
