package com.gmail.gm.jcant.javaPro;

public class Utils {
    private static final String server = "http://127.0.0.1";
    private static final String path = "/HW41ChatEEServer";
    private static final int PORT = 9090;

    public static String getURL() {
        return server + ":" + PORT + path;
    }
}
