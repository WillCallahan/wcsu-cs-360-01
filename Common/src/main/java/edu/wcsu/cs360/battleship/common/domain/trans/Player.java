package edu.wcsu.cs360.battleship.common.domain.trans;

public class Player {
	private int id;
	private Board board = new Board();
	
	Player(int id) {
		this.id = id;
	}
	
	public Boolean guess() {
		return false;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
}
