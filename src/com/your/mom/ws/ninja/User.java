package com.your.mom.ws.ninja;

public class User {
	private String name;
	private long score;
	private int gameID;

	public String getUser() {
		return name;
	}

	public void setUser(String name) {
		this.name = name;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
}