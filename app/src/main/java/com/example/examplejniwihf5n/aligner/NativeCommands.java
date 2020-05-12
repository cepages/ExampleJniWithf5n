package com.example.examplejniwihf5n.aligner;

public class NativeCommands {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private static NativeCommands nativeCommands;

    private NativeCommands() {

    }

    public static NativeCommands getNativeInstance() {
        if (nativeCommands == null) {
            nativeCommands = new NativeCommands();
        }
        return nativeCommands;
    }

    public native int init(String command, int command_id);
}

