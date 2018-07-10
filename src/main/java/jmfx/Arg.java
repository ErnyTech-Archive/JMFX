package jmfx;

public class Arg {
    private String argName;
    private Object argValue;

    public Arg(String argName, Object argValue) {
        this.argName = argName;
        this.argValue = argValue;
    }

    public String getArgName() {
        return this.argName;
    }

    public Object getArgValue() {
        return this.argValue;
    }
}
