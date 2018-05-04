package com.gmail.gm.jcant.javaPro;

import java.util.Set;

public class UserKickThread implements Runnable{
	
	private static final long TIMEOUT = 1800_000; //30 min.
	private UserList userList;
	
	public UserKickThread() {
		super();
		userList = UserList.getInstance();
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			Set<String> keys = userList.getUsers().keySet();
			for (String key : keys) {
				User u = userList.getUser(key);
				if (u.isOnline()) {
					if ((System.currentTimeMillis() - u.getLastActive()) > TIMEOUT) {
						u.setOnline(false);
						System.out.println("User: "+u.getLogin()+" is AFK - kickout!");
					}
				}
			}
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
