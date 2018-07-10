package jmfx;

import javafx.application.Platform;
import javafx.stage.Stage;
import jmfx.util.Executor;
import jmfx.util.GetInitialized;
import jmfx.util.StandardObjects;
import jmfx.util.exception.FxIdMissingException;

import java.nio.file.Paths;

public class Jmfx {

	public static void autoStartup() {
        if (!isInitialized()) {
            startup();
        }
    }

    public static void startup() {
        Platform.startup(StandardObjects.emptyRunnable);
    }

    public static void run(Runnable runnable) {
        autoStartup();
        Platform.runLater(runnable);
    }

    public static boolean isInitialized() {
        try {
            return GetInitialized.isInitialized();
        } catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException("Problem with JavaFX Library, can't get initialized field from com.sun.javafx.application.PlatformImpl class");
        }
    }
}
