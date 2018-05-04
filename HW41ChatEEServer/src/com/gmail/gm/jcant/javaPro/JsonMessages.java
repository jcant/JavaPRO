package com.gmail.gm.jcant.javaPro;

import java.util.*;

public class JsonMessages {
    private final List<Message> list;

    public JsonMessages(Map<Integer, Message> sourceList, int fromIndex, User user) {
        this.list = new ArrayList<>();
        Set<Integer> keys = sourceList.keySet();
        for (Integer key : keys) {
            // System.out.println(key);
            if (key > fromIndex) {
                Message m = sourceList.get(key);
                //System.out.println("message: "+m);
                //System.out.println("user: "+user);
                Optional<String> mTo = Optional.ofNullable(m.getTo());
                Optional<String> mRoom = Optional.ofNullable(m.getRoom());
                Optional<String> uRoom = Optional.ofNullable(user.getRoom());
                if (((!mTo.isPresent())&&(mRoom.orElse("null").equals(uRoom.orElse("null")))) /*message to all in the same room*/
                ||(mTo.orElse("null").equals(user.getLogin())||(m.getFrom().equals(user.getLogin())))) /* message to user or from user*/
                {
                    list.add(m);
                }
            }
        }
    }

    public List<Message> getList() {
        return Collections.unmodifiableList(list);
    }
}
