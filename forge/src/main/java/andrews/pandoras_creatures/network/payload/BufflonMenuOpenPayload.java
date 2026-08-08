package andrews.pandoras_creatures.forge.network.payload;

/**
 * 1.20.2+: Forge elimino NetworkHooks.openScreen (que enviaba datos extra junto con la
 * apertura del menu). Sin reemplazo directo en Forge 48.1.0 (verificado: MenuProvider,
 * IForgeMenuType y ClientboundOpenScreenPacket no ganaron ningun hook de datos extra).
 * Se sincroniza el entityId con un payload propio enviado justo antes de openMenu(),
 * igual de confiable por orden FIFO de la misma conexion. Ver ForgeMenuBridge.
 */
public record BufflonMenuOpenPayload(int entityId) {
}
