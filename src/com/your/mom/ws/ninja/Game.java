package com.your.mom.ws.ninja;

public class Game extends Thread {

	// We'll allow 4 players for now
	private static User[] users = new User[4];
	// Twerking indicates whether the game is running
	private boolean twerking = true;
	private static int lastUser = 0;

	public void run() {
		while (!twerking) {

		}
	}

	public boolean isGameFull() {
		return (users.length == lastUser);
	}

	public boolean addUser(User user) {
		if (users.length == lastUser)
			return false;
		user.setGameID(lastUser);
		users[lastUser++] = user;
		return true;
	}

	public boolean removeUser(User user) {
		// TODO: Decide whether I want to find the user or assign a user ID
		return true;

	}

	public boolean isThreadChilling() {
		return !this.twerking;
	}

	public int numberOfPlayas() {
		return users.length;
	}
}