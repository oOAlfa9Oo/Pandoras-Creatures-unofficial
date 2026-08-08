package andrews.pandoras_creatures.forge.datagen.model;

import andrews.pandoras_creatures.forge.registry.PCForgeBlocks;
import andrews.pandoras_creatures.registry.block.PCBlockIds;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxPalette;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * Copia forge-especifica de andrews.pandoras_creatures.datagen.model.PCBlockStateModelDataProvider
 * (neoforge). BlockStateProvider es una clase de datagen de loader (net.minecraftforge.client.model
 * .generators.* en forge, net.neoforged.neoforge.client.model.generators.* en neoforge -- misma API,
 * paquete distinto, confirmado contra el .java fuente de ambos jars resueltos), asi que no puede vivir
 * en common bajo la regla de verifyArchitecture. Logica identica a la version neoforge.
 */
public final class PCForgeBlockStateModelDataProvider extends BlockStateProvider {
    public PCForgeBlockStateModelDataProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Reference.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerArachnonCrystal();
        registerPlant(PCForgeBlocks.getSimpleBlock(PCBlockIds.HORSETAIL));
        registerPlant(PCForgeBlocks.getSimpleBlock(PCBlockIds.DHANIA));
        registerPlant(PCForgeBlocks.getSimpleBlock(PCBlockIds.HILL_BLOOM));
        registerEndTrollBoxes();
        registerParticleOnlyBlock(PCForgeBlocks.getSimpleBlock(PCBlockIds.PANDORIC_SHARD), modLoc("block/particles/pandoric_shard"));
    }

    private void registerArachnonCrystal() {
        Block block = PCForgeBlocks.getSimpleBlock(PCBlockIds.ARACHNON_CRYSTAL);
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
        Block block = PCForgeBlocks.getEndTrollBox(color);
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
