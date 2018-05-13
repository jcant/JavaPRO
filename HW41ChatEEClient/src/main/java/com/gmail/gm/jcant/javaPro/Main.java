package com.gmail.gm.jcant.javaPro;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        UserWorker uWorker = new UserWorker();
        uWorker.doAuth();

        Thread th = new Thread(new GetThread(uWorker));
        th.setDaemon(true);
        th.start();

        ChatProcessor chatProcessor = new ChatProcessor(uWorker);

        try {
            chatProcessor.mainLoop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
