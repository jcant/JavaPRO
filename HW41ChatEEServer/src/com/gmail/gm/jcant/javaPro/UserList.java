package com.gmail.gm.jcant.javaPro;

import java.util.HashMap;
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
        } else if ((!u.isOnline()) && (u.getPassword().equals(password))) {
            key = u.generateKey();
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
            u.setOnline(false);
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
}
