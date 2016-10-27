package edu.wcsu.cs360.battleship.common.domain.dmo.local;

public class Player {
	private int ID;

	Player(int ID) {
		this.ID = ID;
	}
	
	Board usrboard = new Board();

}
