package com.gmail.gm.jcant.javaPro;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Auth auth = new Auth();
        auth.doAuth();

        Thread th = new Thread(new GetThread(auth.getLogin()));
        th.setDaemon(true);
        th.start();

        ChatProcessor chatProcessor = new ChatProcessor(auth);

        try {
            chatProcessor.mainLoop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
