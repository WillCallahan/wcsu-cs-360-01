package edu.wcsu.cs360.battleship.common.domain.dmo.local;

public class Tuple {
	private int x;
	private int y;
	
	public Tuple(int i, int j) {
		x = i;
		y = j;
	}

	public int size(Tuple p2) {
		if (x == p2.x) {
			return Math.abs(y - p2.y);
		} else {
			return Math.abs(x - p2.x);
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
