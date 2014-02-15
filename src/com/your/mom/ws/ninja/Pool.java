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
				String message = in.readUTF();
				if (message.equalsIgnoreCase("new")) {
					int x;
					Boolean success = false;
					for (x = 0; x < games.length; x++)
						if (games[x].isThreadChilling()) {
							// We will need a mirror of the user object in the
							// app
							success = games[x].addUser((User) in.readObject());
							break;
						}
					if (!success)
						out.writeUTF("FAILED NO ROOMS AVAILABLE OR SOMETHING");

				} else if ((message.toLowerCase()).contains("join")) {
					if (!games[Integer.parseInt(message.substring(4,
							message.length()))].addUser((User) in.readObject()))
						out.writeUTF("FAILED THIS ROOM IS FULL MAYBE");
				} else {
					System.out
							.println("Something went wrong - received message"
									+ message);
				}

			}
		}
	}
}
