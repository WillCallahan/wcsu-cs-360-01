package edu.wcsu.cs360.battleship.common.domain.trans;

public class Blindboard {
	boolean[][] brd = new boolean[5][5];
	
	public void mark(int x, int y) {
		brd[x][y] = true;
	}
}
