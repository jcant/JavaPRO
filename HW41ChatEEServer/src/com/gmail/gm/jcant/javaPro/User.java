package com.gmail.gm.jcant.javaPro;

public class User {
    private String login;
    private String password;
    private boolean online = false;
    private long lastActive;
    private String currentKey;
    private String room;

    public User() {
        super();
    }

    public User(String login, String password) {
        super();
        this.login = login;
        this.password = password;
        this.room = null;
    }

    public String generateKey(){
        StringBuilder sb = new StringBuilder();
        sb.append(login).append(password).append(System.nanoTime());
        currentKey = "" + sb.toString().hashCode();
        lastActive = System.currentTimeMillis();
        online = true;
        room = null;
        return currentKey;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public long getLastActive() {
        return lastActive;
    }

    public void setLastActive(long lastActive) {
        this.lastActive = lastActive;
    }

    public String getCurrentKey() {
        return currentKey;
    }

    public void setCurrentKey(String currentKey) {
        this.currentKey = currentKey;
    }

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", password=" + password + ", online=" + online + ", lastActive=" + lastActive
				+ ", currentKey=" + currentKey + "]";
	}

    

}
