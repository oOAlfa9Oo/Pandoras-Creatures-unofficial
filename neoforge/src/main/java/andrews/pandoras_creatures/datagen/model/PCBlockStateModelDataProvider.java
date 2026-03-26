package andrews.pandoras_creatures.datagen.model;

import andrews.pandoras_creatures.registry.PCBlocks;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxPalette;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public final class PCBlockStateModelDataProvider extends BlockStateProvider {
    public PCBlockStateModelDataProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Reference.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerArachnonCrystal();
        registerPlant(PCBlocks.HORSETAIL.get());
        registerPlant(PCBlocks.DHANIA.get());
        registerPlant(PCBlocks.HILL_BLOOM.get());
        registerEndTrollBoxes();
        registerParticleOnlyBlock(PCBlocks.PANDORIC_SHARD.get(), modLoc("block/particles/pandoric_shard"));
    }

    private void registerArachnonCrystal() {
        Block block = PCBlocks.ARACHNON_CRYSTAL.get();
        simpleBlock(block, models().getExistingFile(modLoc("block/" + path(block))));
    }

    private void registerPlant(Block block) {
        String path = path(block);
        ModelFile model = models().cross(path, modLoc("block/" + path)).renderType("cutout");
        simpleBlock(block, model);
        itemModels()
                .getBuilder(path)
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", modLoc("block/" + path));
    }

    private void registerEndTrollBoxes() {
        registerEndTrollBox(null);
        for (DyeColor color : PCEndTrollBoxPalette.orderedColors()) {
            registerEndTrollBox(color);
        }
    }

    private void registerEndTrollBox(DyeColor color) {
        Block block = PCBlocks.getEndTrollBox(color);
        registerParticleOnlyBlock(block, modLoc("block/particles/" + path(block)));
    }

    private void registerParticleOnlyBlock(Block block, ResourceLocation particleTexture) {
        String path = path(block);
        simpleBlock(block, models().getBuilder(path).texture("particle", particleTexture));
    }

    private static String path(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }
}
