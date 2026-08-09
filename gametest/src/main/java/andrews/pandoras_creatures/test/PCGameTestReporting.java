package andrews.pandoras_creatures.test;

import net.minecraft.gametest.framework.GlobalTestReporter;
import net.minecraft.gametest.framework.JUnitLikeTestReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Instala el reporter JUnit-XML nativo de GameTest (GlobalTestReporter/JUnitLikeTestReporter,
 * ambos parte de Minecraft desde 1.20.1 hasta 1.21.1, verificado via javap) cuando el run de
 * runGameTestServer pasa la ruta de destino por system property. GameTestServer ya llama
 * GlobalTestReporter.finish() al apagarse, que a su vez guarda el XML solo -- no hace falta
 * ningun mixin ni hook adicional.
 */
public final class PCGameTestReporting {
    private static final String REPORT_PATH_PROPERTY = "pandoras_creatures.gameTestReportPath";
    private static final Logger LOGGER = LoggerFactory.getLogger(PCGameTestReporting.class);

    private PCGameTestReporting() {
    }

    public static void installIfRequested() {
        String reportPath = System.getProperty(REPORT_PATH_PROPERTY);
        if (reportPath == null || reportPath.isBlank()) {
            return;
        }

        File destination = new File(reportPath);
        File parent = destination.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }

        try {
            GlobalTestReporter.replaceWith(new JUnitLikeTestReporter(destination));
        } catch (Exception e) {
            LOGGER.error("No se pudo instalar el reporter JUnit-XML de gametests en {}", reportPath, e);
        }
    }
}
