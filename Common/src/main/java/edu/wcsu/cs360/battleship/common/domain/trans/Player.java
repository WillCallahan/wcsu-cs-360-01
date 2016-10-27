package edu.wcsu.cs360.battleship.common.domain.trans;

public class Player {
	private int ID;

	Player(int ID) {
		this.ID = ID;
	}
	
	Board usrboard = new Board();

}
