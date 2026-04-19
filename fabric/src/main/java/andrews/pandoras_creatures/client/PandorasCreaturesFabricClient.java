package andrews.pandoras_creatures.client;

import andrews.pandoras_creatures.client.bootstrap.PCFabricClientBlockRegistry;
import andrews.pandoras_creatures.client.bootstrap.PCFabricClientEntityRegistry;
import andrews.pandoras_creatures.client.bootstrap.PCFabricClientItemRegistry;
import andrews.pandoras_creatures.client.bootstrap.PCFabricClientScreenRegistry;
import andrews.pandoras_creatures.client.bootstrap.PCFabricSpecialModelRegistry;
import andrews.pandoras_creatures.client.events.FabricRiderInventoryHandler;
import andrews.pandoras_creatures.client.item.FabricPlantHatArmorRenderer;
import andrews.pandoras_creatures.client.network.FabricAnimationPayloadClientHandler;
import net.fabricmc.api.ClientModInitializer;

public final class PandorasCreaturesFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FabricAnimationPayloadClientHandler.registerReceiver();
        PCFabricClientEntityRegistry.registerAll();
        PCFabricClientBlockRegistry.registerAll();
        PCFabricClientItemRegistry.registerAll();
        PCFabricSpecialModelRegistry.registerAll();
        PCFabricClientScreenRegistry.registerAll();
        FabricRiderInventoryHandler.register();
        FabricPlantHatArmorRenderer.register();
    }
}
