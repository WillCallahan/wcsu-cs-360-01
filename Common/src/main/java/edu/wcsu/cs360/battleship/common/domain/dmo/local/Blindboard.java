package edu.wcsu.cs360.battleship.common.domain.dmo.local;

public class Blindboard {
	private boolean[][] brd = new boolean[5][5];

	public void mark(int x, int y) {
		brd[x][y] = true;
	}
}
