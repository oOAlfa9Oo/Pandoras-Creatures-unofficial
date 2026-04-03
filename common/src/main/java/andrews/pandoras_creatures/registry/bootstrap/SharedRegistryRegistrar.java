package andrews.pandoras_creatures.registry.bootstrap;

import java.util.function.Supplier;

/**
 * Loader-neutral registration adapter for shared content bootstraps.
 */
@FunctionalInterface
public interface SharedRegistryRegistrar<T, H extends Supplier<? extends T>> {
    H register(String id, Supplier<? extends T> factory);
}
