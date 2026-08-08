package andrews.pandoras_creatures.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.MapLike;

import java.util.stream.Stream;

/**
 * Convierte un Codec "map-shaped" (objeto JSON plano, sin envoltorio) en un MapCodec real,
 * preservando esa forma para Codec.dispatch (usado por Recipe.CODEC via
 * RECIPE_SERIALIZER.byNameCodec().dispatch(...)). Sin esto, envolver un Codec vanilla con
 * .xmap() lo vuelve opaco para el dispatch, que entonces espera un campo anidado "value" en
 * vez de fusionar los campos al nivel superior del JSON (produce "Not a JSON object: null" en
 * 1.20.4). Equivalente a MapCodec.assumeMapUnsafe(), no disponible en la version de
 * DataFixerUpper (6.0.8) que trae esta version de Minecraft.
 */
final class PCMapCodecs {
    private PCMapCodecs() {
    }

    static <A> MapCodec<A> assumeMap(Codec<A> codec) {
        return MapCodec.of(new com.mojang.serialization.MapEncoder<A>() {
            @Override
            public <T> com.mojang.serialization.RecordBuilder<T> encode(A input, DynamicOps<T> ops, com.mojang.serialization.RecordBuilder<T> prefix) {
                T encoded = codec.encodeStart(ops, input).getOrThrow(false, error -> {
                });
                ops.getMap(encoded).result().ifPresent(map -> map.entries().forEach(pair -> prefix.add(pair.getFirst(), pair.getSecond())));
                return prefix;
            }

            @Override
            public <T> Stream<T> keys(DynamicOps<T> ops) {
                return Stream.empty();
            }

            @Override
            public <T> com.mojang.serialization.KeyCompressor<T> compressor(DynamicOps<T> ops) {
                return new com.mojang.serialization.KeyCompressor<>(ops, Stream.empty());
            }
        }, new com.mojang.serialization.MapDecoder<A>() {
            @Override
            public <T> DataResult<A> decode(DynamicOps<T> ops, MapLike<T> input) {
                T reconstructed = ops.createMap(input.entries());
                return codec.parse(ops, reconstructed);
            }

            @Override
            public <T> Stream<T> keys(DynamicOps<T> ops) {
                return Stream.empty();
            }

            @Override
            public <T> com.mojang.serialization.KeyCompressor<T> compressor(DynamicOps<T> ops) {
                return new com.mojang.serialization.KeyCompressor<>(ops, Stream.empty());
            }
        });
    }
}
