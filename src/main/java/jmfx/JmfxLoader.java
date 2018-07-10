package jmfx;

import javafx.fxml.FXMLLoader;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.Parent;
import jmfx.util.exception.FxIdMissingException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

public class JmfxLoader {
    private FXMLLoader fxmlLoader;
    private Parent parentRoot;

    public JmfxLoader(String path) {
        try {
            this.fxmlLoader = new FXMLLoader(Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getResource(path));
        } catch (ClassNotFoundException e) {
            this.fxmlLoader = null;
        }
    }

    public JmfxLoader(Path path) {
        try {
            this.fxmlLoader = new FXMLLoader(path.toUri().toURL());
        } catch (MalformedURLException e) {
            this.fxmlLoader = null;
        }
    }

    public JmfxLoader(File file) {
        try {
            this.fxmlLoader = new FXMLLoader(file.toURI().toURL());
        } catch (MalformedURLException e) {
            this.fxmlLoader = null;
        }
    }

    public void load() {
        this.parentRoot = loader();
    }

    public Parent getParent() {
        return this.parentRoot;
    }

    @SuppressWarnings("unchecked")
    public <T extends Node> T getId(String fxId) throws FxIdMissingException {
        if (this.fxmlLoader == null || this.parentRoot == null) {
            return null;
        }

        if (this.fxmlLoader.getNamespace() == null) {
            return null;
        }

        T fxObj;
        var objFxId = this.fxmlLoader.getNamespace().get(fxId);

        if (objFxId == null) {
            throw new FxIdMissingException(fxId);
        }

        try {
            fxObj = (T) objFxId.getClass().cast(objFxId);
        } catch (ClassCastException e) {
            fxObj = null;
        }

        return fxObj;
    }

    private Parent loader() {
        if (this.fxmlLoader == null) {
            return null;
        }

        var jmfxRun = new JmfxRun<Parent>();
        var parentFxml = jmfxRun.invoke(args -> {
            try {
                return this.fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        });

        if (parentFxml != null) {
            parentFxml.setCache(true);
            parentFxml.setCacheHint(CacheHint.SPEED);
        }

        return parentFxml;
    }
}
