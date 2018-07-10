package jmfx.util;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicBoolean;

public class GetInitialized {
    private static Unsafe unsafe;

    public static boolean isInitialized() throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        var platformClass = Class.forName("com.sun.javafx.application.PlatformImpl");
        var initializedField = platformClass.getDeclaredField("initialized");
        var initialized = (AtomicBoolean) getUnsafe().getObject(platformClass, getUnsafe().staticFieldOffset(initializedField));
        return initialized.get();
    }


    private static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        if (GetInitialized.unsafe == null) {
            var unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            GetInitialized.unsafe = (Unsafe) unsafeField.get(null);
        }

        return GetInitialized.unsafe;
    }
}
