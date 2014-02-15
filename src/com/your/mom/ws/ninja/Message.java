package com.your.mom.ws.ninja;

public class Message {
	private boolean newGame = false;
	private boolean joinGame = false;
	private boolean gameEvent = false;
	private int gameID = -1;
	private long score = -1;
	private int rank = -1;
	private User user = null;
	private String text = "";
	private boolean success;
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public boolean isNewGame() {
		return newGame;
	}

	public void setNewGame(boolean newGame) {
		this.newGame = newGame;
	}

	public boolean isJoinGame() {
		return joinGame;
	}

	public void setJoinGame(boolean joinGame) {
		this.joinGame = joinGame;
	}

	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isGameEvent() {
		return gameEvent;
	}

	public void setGameEvent(boolean gameEvent) {
		this.gameEvent = gameEvent;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
