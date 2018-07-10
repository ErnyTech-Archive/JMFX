package jmfx;

import jmfx.util.Executor;

import java.util.concurrent.atomic.AtomicReference;

public class JmfxRun<T> {
    private Args args;
    private volatile boolean isRunning = true;

    public void setArgs(Args args) {
        this.args = args;
    }

    public Void execute(Executor<Void> executor, Args args) {
        this.isRunning = true;

        Jmfx.run(() -> {
            executor.invoke(args);
            this.isRunning = false;
        });

        return null;
    }

    public void execute(Executor<Void> executor) {
        execute(executor, this.args);
    }

    public T invoke(Executor<T> executor, Args args) {
        this.isRunning = true;
        var returnObj = new AtomicReference<T>();

        Jmfx.run(() -> {
          returnObj.set(executor.invoke(args));
          this.isRunning = false;
        });

        waitFor();
        return returnObj.get();
    }

    public T invoke(Executor<T> executor) {
        return invoke(executor, this.args);
    }

    public void waitFor() {
        while (true) {
            if (!isRunning) {
                break;
            }
        }
    }
}
