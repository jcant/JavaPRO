package com.gmail.gm.jcant.javaPro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonMessages {
    private final List<Message> list;
    private int size;

    public JsonMessages(List<Message> sourceList, int fromIndex) {
        this.list = new ArrayList<>();
        for (int i = fromIndex; i < sourceList.size(); i++)
            list.add(sourceList.get(i));
        this.size = sourceList.size();
    }

    public List<Message> getList() {
        return Collections.unmodifiableList(list);
    }

    public int getSize(){
        return size;
    }

    @Override
    public String toString() {
        return "JsonMessages{" +
                "list=" + list +
                ", size=" + size +
                '}';
    }
}
