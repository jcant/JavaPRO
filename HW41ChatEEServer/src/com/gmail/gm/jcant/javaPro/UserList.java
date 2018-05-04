package com.gmail.gm.jcant.javaPro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserList {
	private static UserList userList = new UserList();

	private Map<String, User> users = new HashMap<>();

	private UserList() {
		super();
	}

	public static UserList getInstance() {
		return userList;
	}

	public String authUser(String login, String password) {
		String key = null;
		User u = findUser(login);
		if (u == null) {
			User newUser = new User(login, password);
			key = newUser.generateKey();
			users.put(key, newUser);
			// System.out.println("Add new user: " + login);
			// System.out.println("*key: " + newUser.getCurrentKey());
			// System.out.println("*online: " + newUser.isOnline());
		} else if ((!u.isOnline()) && (u.getPassword().equals(password))) {
			users.remove(u.getCurrentKey());
			key = u.generateKey();
			users.put(key, u);
			// System.out.println("reAuth existing user: " + login);
			// System.out.println("*key: " + u.getCurrentKey());
			// System.out.println("*online: " + u.isOnline());
		}

		return key;
	}

	public boolean checkUser(String key) {
		User u = users.get(key);
		if ((u != null) && (u.isOnline())) {
			return true;
		}
		return false;
	}

	public void logoutUser(String login) {
		User u = findUser(login);
		if (u != null) {
			// System.out.println("Logout user: " + u.getLogin());
			u.setOnline(false);
			u.setRoom(null);
		}
	}

	private User findUser(String login) {
		Set<String> keys = users.keySet();
		for (String key : keys) {
			User u = users.get(key);
			if (u.getLogin().equals(login)) {
				return u;
			}
		}
		return null;
	}

	public User getUser(String key) {
		return users.get(key);
	}

	public User getAuthUser(String key) {
		User u = userList.getUser(key);
		if ((u != null) && (u.isOnline())) {
			return u;
		} else {
			return null;
		}
	}

	public Map<String, User> getUsers() {
		return users;
	}

	public List<String> getUsersStatusList(String login) {
		List<String> result = new ArrayList<>();

		Set<String> keys = users.keySet();
		for (String key : keys) {
			User u = users.get(key);
			if ((login == null) || (login.equals(u.getLogin()))) {
				result.add(u.getLogin() + " online=" + u.isOnline() + " room=" + u.getRoom());
			}
		}
		return result;
	}

}
