package com.gmail.gm.jcant.javaPro;

import java.util.ArrayList;
import java.util.List;

public class JsonMessages {
    private final List<Message> list;
    private int size;

    public JsonMessages(List<Message> sourceList, int fromIndex, String user) {
        //System.out.println("from="+fromIndex+" user="+user);
        this.list = new ArrayList<>();

        for (int i = fromIndex; i < sourceList.size(); i++) {
            Message m = sourceList.get(i);
            //System.out.println("MESSAGE:"+m);
            if (m.getTo()==null || m.getTo().equals(user) || m.getFrom().equals(user)) {
                list.add(m);
            }
        }

        size = sourceList.size();
    }

    public int getSize(){
        return size;
    }
}
