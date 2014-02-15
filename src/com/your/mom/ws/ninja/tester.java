package com.your.mom.ws.ninja;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class tester {
	public static void main(String [] args) throws UnknownHostException, IOException, ClassNotFoundException
	{
		User testUser = new User();
		Message message = new Message();
		message.setNewGame(true);
		message.setUser(testUser);
		Socket test = null;
		String hostname = null;
		try {
			InetAddress addr = InetAddress.getLocalHost();
			hostname = addr.getHostName();
		} catch (UnknownHostException e) {
		}
		test = new Socket(hostname, 8089);
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		out = new ObjectOutputStream(test.getOutputStream());
		in = new ObjectInputStream(test.getInputStream());
		out.writeObject(message);
		Message messageIn = (Message) in.readObject();
		out.close();
		in.close();
		test.close();
	}
}
