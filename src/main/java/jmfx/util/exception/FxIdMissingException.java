package jmfx.util.exception;

public class FxIdMissingException extends Exception {
    public FxIdMissingException(String fxId) {
        super("The object " + fxId + " missing in fxml file");
    }
}