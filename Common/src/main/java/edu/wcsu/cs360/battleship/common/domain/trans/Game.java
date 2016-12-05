package edu.wcsu.cs360.battleship.common.domain.trans;

import java.io.Serializable;

public class Game implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Player playerOne;
	private Player playerTwo;
	
	public Game() {
		
	}
	
	public Player getPlayerOne() {
		return playerOne;
	}
	
	public void setPlayerOne(Player playerOne) {
		this.playerOne = playerOne;
	}
	
	public Player getPlayerTwo() {
		return playerTwo;
	}
	
	public void setPlayerTwo(Player playerTwo) {
		this.playerTwo = playerTwo;
	}
}
