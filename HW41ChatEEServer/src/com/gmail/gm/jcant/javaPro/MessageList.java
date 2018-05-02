package com.gmail.gm.jcant.javaPro;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MessageList {
    private static final MessageList msgList = new MessageList();
    private static final int LIMIT = 10;

    private final Gson gson;
    private final List<Message> list = new ArrayList<>();

    public static MessageList getInstance() {
        return msgList;
    }

    private MessageList() {
        gson = new GsonBuilder().setDateFormat("MMMMM d, yyyy h:m:s a").create();
        //gson = new GsonBuilder().create();
    }

    public synchronized void add(Message m) {
        if (list.size() + 1 == LIMIT) {
            list.remove(0);
            list.remove(0);
        }
        list.add(m);
    }

    public synchronized String toJSON(int n, String user) {
        if (n == list.size()) {
            return null;
        }

        return gson.toJson(new JsonMessages(list, n, user));
    }
}
