package com.your.mom.ws.ninja;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Game extends Thread {

	// We'll allow 2 players for now
	private static User[] users = new User[2];
	// Twerking indicates whether the game is running
	private boolean twerking = false;
	private static int lastUser = 0;
	private static ServerSocket socket = null;

	public Game(ServerSocket s) {
		socket = s;
	}

	public void run() {
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		Socket client = null;
		while (true) {

			// waiting for the last user to enter the game
			while (users[users.length] == null)
				;
			twerking = true;
			while (twerking) {
				try {
					client = socket.accept();
					if (client != null) {
						out = new ObjectOutputStream(client.getOutputStream());
						in = new ObjectInputStream(client.getInputStream());
						Message message = (Message) in.readObject();
						if (message.isGameEvent()) {
							
						}
						
						if(true) //Temporary ready
						{
							out.writeUTF("READY");
						}
					}
				} catch (IOException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			// we can nao begin to receive dat righteous data
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