package jmfx;

import javafx.concurrent.Task;
import javafx.stage.Stage;

public class JmfxWaitClose {
    private Stage stage;

    public JmfxWaitClose(Stage stage) {
        this.stage = stage;
    }

    public void close(Task task) {
        new Thread(() -> {
            while (true) {
                if (task.getProgress() == 1) {
                    stageClose();
                    break;
                }
            }
        }).start();
    }

    private void stageClose() {
        Jmfx.run(() -> {
            this.stage.hide();
            this.stage.close();
        });
    }
}
