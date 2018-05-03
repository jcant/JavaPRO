package com.gmail.gm.jcant.javaPro;

import java.io.IOException;
import java.util.Scanner;

public class ChatProcessor {
    private Scanner scanner = new Scanner(System.in);
    private Auth auth;
    private boolean cont = true;

    public ChatProcessor(Auth auth) {
        super();
        this.auth = auth;
    }

    public void mainLoop() throws IOException {

        System.out.println("Enter your message (!HELP - to help:) ");
        while (cont) {
            String text = scanner.nextLine();
            if (text.isEmpty()) continue;
            if (findCommands(text)) continue;

            Message m = new Message(auth.getKey(), text);
            int res = m.send(Utils.getURL() + "/add");

            if (res != 200) { // 200 OK
                System.out.println("HTTP error occurred: " + res);
                return;
            }
        }

    }

    private boolean findCommands(String text) {
        if (text.startsWith("!EXIT")){
            auth.doExit();
            cont = false;
            return true;
        }
        if (text.startsWith("!HELP")){
            printHelp();
            return true;
        }

        return false;
    }

    private void printHelp(){
        System.out.println("'!HELP'\t\t - print this help info");
        System.out.println("'!TO:login'\t - private message to user=login");
        System.out.println("'!EXIT'\t\t - logout this user");
    }
}
