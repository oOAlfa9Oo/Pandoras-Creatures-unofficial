package andrews.pandoras_creatures;

import andrews.pandoras_creatures.platform.EntityBridge;
import andrews.pandoras_creatures.platform.PlatformServices;
import andrews.pandoras_creatures.platform.RegistryBridge;
import andrews.pandoras_creatures.platform.SidedHooks;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PandorasCreaturesCommonTest {
    @Test
    void initializesOnlyOnceAndExposesPlatformServices() {
        TestPlatformServices first = new TestPlatformServices("test-loader", false);
        TestPlatformServices second = new TestPlatformServices("other-loader", true);

        PandorasCreaturesCommon.initialize(first);
        PandorasCreaturesCommon.initialize(second);

        assertTrue(PandorasCreaturesCommon.isInitialized());
        assertSame(first, PandorasCreaturesCommon.platform());
        assertEquals("test-loader", PandorasCreaturesCommon.platform().loaderName());
        assertFalse(PandorasCreaturesCommon.platform().sidedHooks().isClientEnvironment());
    }

    private record TestPlatformServices(String loaderName, boolean client) implements PlatformServices {
        @Override
        public RegistryBridge registry() {
            return (RegistryBridge) Proxy.newProxyInstance(
                    RegistryBridge.class.getClassLoader(),
                    new Class<?>[]{RegistryBridge.class},
                    (proxy, method, args) -> "namespace".equals(method.getName()) ? "pandoras_creatures" : null
            );
        }

        @Override
        public andrews.pandoras_creatures.platform.NetworkBridge network() {
            return new andrews.pandoras_creatures.platform.NetworkBridge() {
                @Override
                public String channelNamespace() {
                    return "pandoras_creatures";
                }

                @Override
                public void requestBufflonInventory(int entityId) {
                }

                @Override
                public void requestBufflonSit(int entityId, boolean shouldSit) {
                }

                @Override
                public void requestBufflonFollow(int entityId, boolean shouldFollow) {
                }

                @Override
                public void requestBufflonCombatMode(int entityId, boolean combatMode) {
                }
            };
        }

        @Override
        public andrews.pandoras_creatures.platform.MenuBridge menus() {
            return null;
        }

        @Override
        public EntityBridge entities() {
            return null;
        }

        @Override
        public SidedHooks sidedHooks() {
            return () -> client;
        }
    }
}
