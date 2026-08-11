package andrews.pandoras_creatures.client.renderer.special;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderers;
import net.minecraft.resources.ResourceLocation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * NeoForge expone un evento real para esto (RegisterSpecialModelRendererEvent), pero ni Forge
 * 54.1.14 ni Fabric API 0.110.5+1.21.4 tienen un hook de moddeo para registrar tipos custom en
 * SpecialModelRenderers.ID_MAPPER (verificado: Forge solo parchea el evento de bloques
 * -CreateSpecialBlockRendererEvent-, no el de items; Fabric API no tiene ningun modulo
 * relacionado). ID_MAPPER es un campo privado de ExtraCodecs.LateBoundIdMapper poblado en
 * SpecialModelRenderers.bootstrap() (llamado una sola vez al iniciar el cliente); esta clase
 * accede a ese campo con reflection para poder llamar su metodo publico put(id, codec), que es
 * el mismo metodo que usa el evento real de NeoForge por debajo.
 */
public final class PCSpecialModelRendererRegistrar {
    private PCSpecialModelRendererRegistrar() {
    }

    public static void register(ResourceLocation id, MapCodec<? extends SpecialModelRenderer.Unbaked> codec) {
        try {
            Field idMapperField = SpecialModelRenderers.class.getDeclaredField("ID_MAPPER");
            idMapperField.setAccessible(true);
            Object idMapper = idMapperField.get(null);
            Method put = idMapper.getClass().getMethod("put", Object.class, Object.class);
            put.setAccessible(true);
            put.invoke(idMapper, id, codec);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Could not register special model renderer type " + id
                    + " into SpecialModelRenderers.ID_MAPPER (vanilla internals changed?)", e);
        }
    }
}
