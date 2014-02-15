package com.your.mom.ws.ninja;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Pool {

	private static ServerSocket listener = null;
	private static Game[] games = new Game[10];
	static {
		try {
			listener = new ServerSocket(8089, 4);
			for (int x = 0; x < games.length; x++) {
				// assigning sockets to each game
				games[x] = new Game(new ServerSocket(8090 + x, 4));
				games[x].run();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// give 0 fucks therefore we just throw the IOException
	// TODO: Add proper error handling
	public static void main(String args[]) throws IOException,
			ClassNotFoundException {
		ObjectOutputStream out = null;
		ObjectInputStream in = null;

		Socket client = null;
		while (true) {
			client = listener.accept();
			if (client != null) {
				out = new ObjectOutputStream(client.getOutputStream());
				in = new ObjectInputStream(client.getInputStream());
				Message message = (Message) in.readObject();
				if (message.isNewGame()) {
					int x;
					Boolean success = false;
					for (x = 0; x < games.length; x++)
						if (games[x].isThreadChilling()) {
							// We will need a mirror of the user object in the
							// app
							success = games[x].addUser(message.getUser());
							Message sucessMessage = new Message();
							sucessMessage.setSuccess(true);
							sucessMessage.setGameID(x);
							sucessMessage.setText("Succesfully created game");
							out.writeObject(sucessMessage);
							break;
						}
					if (!success) {
						Message failure = new Message();
						message.setSuccess(false);
						message.setText("Something bad happened | No rooms");
						out.writeObject(failure);
					}

				} else if (message.isJoinGame()) {
					if (!games[message.getGameID()].addUser(message.getUser())) {
						Message failure = new Message();
						message.setSuccess(false);
						message.setText("Something bad happened | Rooms Full");
						out.writeObject(failure);
					}
				} else {
					Message failure = new Message();
					message.setSuccess(false);
					message.setText("Something bad happened | Everything was false!?");
					out.writeObject(failure);
				}

			}
		}
	}
}
