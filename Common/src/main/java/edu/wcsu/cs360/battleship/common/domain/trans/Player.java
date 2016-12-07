package edu.wcsu.cs360.battleship.common.domain.trans;

public class Player {
	private long id;
	private Board board;
	
	private Player() {
		
	}
	
	public Player(long id) {
		this.id = id;
	}
	
	public Boolean guess() {
		return false;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
}
