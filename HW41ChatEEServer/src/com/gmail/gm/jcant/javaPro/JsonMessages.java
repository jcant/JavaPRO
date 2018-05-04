package com.gmail.gm.jcant.javaPro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JsonMessages {
	private final List<Message> list;

	public JsonMessages(Map<Integer, Message> sourceList, int fromIndex, User user) {
		this.list = new ArrayList<>();
		Set<Integer> keys = sourceList.keySet();
		for (Integer key : keys) {
			// System.out.println(key);
			if (key > fromIndex) {
				Message m = sourceList.get(key);
				if (m.getTo() == null || m.getTo().equals(user.getLogin()) || m.getFrom().equals(user.getLogin())) {

					if ((user.getRoom() == null) || (user.getRoom().equals(m.getRoom()))) {
						list.add(m);
					}
				}
			}
		}
	}

	public List<Message> getList() {
		return Collections.unmodifiableList(list);
	}
}
