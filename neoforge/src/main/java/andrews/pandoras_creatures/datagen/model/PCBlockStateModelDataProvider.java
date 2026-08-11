package andrews.pandoras_creatures.datagen.model;

import andrews.pandoras_creatures.registry.PCBlocks;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxPalette;
import andrews.pandoras_creatures.util.Reference;
import com.google.gson.JsonObject;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.stream.Stream;

// ADR-0006 / 1.21.4: NeoForge elimino BlockStateProvider/ItemModelProvider/ModelFile por completo
// (Forge todavia los tiene, deprecados). Este provider usa directamente el
// ModelProvider/BlockModelGenerators vanilla (patcheado por NeoForge para aceptar un modId), ya
// que es la unica API que sigue existiendo para generar blockstates + models/block en 1.21.4.
// getKnownItems() se vacia porque los items los genera PCItemModelDataProvider en un provider
// separado (mismo split que existia antes de este port).
public final class PCBlockStateModelDataProvider extends ModelProvider {
    public PCBlockStateModelDataProvider(PackOutput output) {
        super(output, Reference.MODID);
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        return Stream.empty();
    }

    @Override
    public String getName() {
        // ModelProvider#getName() por defecto es "Model Definitions - " + modId, igual en este
        // provider y en PCItemModelDataProvider; DataGenerator.addProvider() indexa por nombre y
        // tira "Duplicate provider" si dos providers comparten el mismo nombre.
        return super.getName() + " (blocks)";
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        registerArachnonCrystal(blockModels);
        registerCrossPlant(blockModels, PCBlocks.HORSETAIL.get());
        registerCrossPlant(blockModels, PCBlocks.DHANIA.get());
        registerCrossPlant(blockModels, PCBlocks.HILL_BLOOM.get());
        registerEndTrollBoxes(blockModels);
        registerParticleOnlyBlock(blockModels, PCBlocks.PANDORIC_SHARD.get(), modLoc("block/particles/pandoric_shard"));
    }

    private void registerArachnonCrystal(BlockModelGenerators blockModels) {
        // El modelo del bloque (models/block/arachnon_crystal.json) esta hecho a mano en los
        // recursos principales, no se genera aca; solo hace falta el blockstate.
        Block block = PCBlocks.ARACHNON_CRYSTAL.get();
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, modLoc("block/" + path(block))));
    }

    private void registerCrossPlant(BlockModelGenerators blockModels, Block block) {
        String path = path(block);
        ResourceLocation modelLoc = modLoc("block/" + path);
        ResourceLocation texture = modLoc("block/" + path);
        blockModels.modelOutput.accept(modelLoc, () -> {
            JsonObject json = new JsonObject();
            json.addProperty("parent", "minecraft:block/cross");
            json.addProperty("render_type", "minecraft:cutout");
            JsonObject textures = new JsonObject();
            textures.addProperty("cross", texture.toString());
            json.add("textures", textures);
            return json;
        });
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, modelLoc));
    }

    private void registerEndTrollBoxes(BlockModelGenerators blockModels) {
        registerEndTrollBox(blockModels, null);
        for (DyeColor color : PCEndTrollBoxPalette.orderedColors()) {
            registerEndTrollBox(blockModels, color);
        }
    }

    private void registerEndTrollBox(BlockModelGenerators blockModels, DyeColor color) {
        Block block = PCBlocks.getEndTrollBox(color);
        registerParticleOnlyBlock(blockModels, block, modLoc("block/particles/" + path(block)));
    }

    private void registerParticleOnlyBlock(BlockModelGenerators blockModels, Block block, ResourceLocation particleTexture) {
        String path = path(block);
        ResourceLocation modelLoc = modLoc("block/" + path);
        blockModels.modelOutput.accept(modelLoc, () -> {
            JsonObject json = new JsonObject();
            JsonObject textures = new JsonObject();
            textures.addProperty("particle", particleTexture.toString());
            json.add("textures", textures);
            return json;
        });
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, modelLoc));
    }

    private static ResourceLocation modLoc(String path) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MODID, path);
    }

    private static String path(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }
}
