package jmfx.util;
import jmfx.Args;

public interface Executor<T> {
    T invoke(Args args);
}
