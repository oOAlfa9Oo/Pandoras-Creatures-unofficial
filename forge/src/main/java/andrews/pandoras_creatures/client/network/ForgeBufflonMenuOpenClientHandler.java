package andrews.pandoras_creatures.forge.client.network;

/**
 * Guarda el entityId del ultimo BufflonMenuOpenPayload recibido, para que el
 * IContainerFactory registrado en PCForgeMenuTypes lo consuma al reconstruir el menu
 * en el cliente. Ver BufflonMenuOpenPayload para el porque.
 */
public final class ForgeBufflonMenuOpenClientHandler {
    private static int pendingEntityId = -1;

    private ForgeBufflonMenuOpenClientHandler() {
    }

    public static void handle(int entityId) {
        pendingEntityId = entityId;
    }

    public static int consumePendingEntityId() {
        int id = pendingEntityId;
        pendingEntityId = -1;
        return id;
    }
}
