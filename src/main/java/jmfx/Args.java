package jmfx;

import java.util.ArrayList;
import java.util.List;

public class Args {
    private List<Arg> args = new ArrayList<>();
    private List<String> argsValueList = new ArrayList<>();
    private List<Object> argsValueObjectList = new ArrayList<>();

    public Args(Arg... args) {
        for(Arg arg : args) {
            add(arg);
        }
    }

    public void add(Arg arg) {
        for (Arg listArg : this.args) {
            if (arg.getArgName().equals(listArg.getArgName())) {
                return;
            }
        }

        this.args.add(arg);

        if (String.class.isInstance(arg.getArgValue())) {
            this.argsValueList.add((String) arg.getArgValue());
        } else {
            this.argsValueList.add(null);
        }

        this.argsValueObjectList.add(arg.getArgValue());
    }

    public void add(String argName, String argValue) {
        add(new Arg(argName, argValue));
    }

    public Object getArgObject (String argName) {
        for (Arg arg : this.args) {
            if (arg.getArgName().equals(argName)) {
                return arg.getArgValue();
            }
        }

        return null;
    }

    public String getArg(String argName) {
        return (String) getArgObject(argName);
    }

    public List<String> getArgs() {
        return this.argsValueList;
    }

    public List<Object> getArgsObject() {
        return this.argsValueObjectList;
    }
}
