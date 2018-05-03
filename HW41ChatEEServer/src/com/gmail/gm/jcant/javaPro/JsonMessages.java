package com.gmail.gm.jcant.javaPro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JsonMessages {
	private final List<Message> list;

	public JsonMessages(Map<Integer, Message> sourceList, int fromIndex, String user) {
		this.list = new ArrayList<>();
		Set<Integer> keys = sourceList.keySet();
		for (Integer key : keys) {
			if (key >= fromIndex) {
				Message m = sourceList.get(key);
				if (m.getTo() == null || m.getTo().equals(user) || m.getFrom().equals(user)) {
					list.add(m);
				}
			}
		}
	}
	
	public List<Message> getList() {
        return Collections.unmodifiableList(list);
    }
}
