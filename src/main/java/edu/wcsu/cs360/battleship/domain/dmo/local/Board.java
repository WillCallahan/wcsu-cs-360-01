package edu.wcsu.cs360.battleship.domain.dmo.local;

public class Board {
	private Tuple size;
	
	private byte[][] brd;
	
	private ShipList shl;
	
	Board() {
		size = new Tuple(5, 5);
		brd = new byte[size.getX()][size.getY()];
		shl = new ShipList((byte) 3);
		
		shl.add_ship(new Tuple(2, 3), new Tuple(2, 2), 1);
		
		
		for (int i = 1; i < shl.getLive().length; i++) {
			place_ship(shl.getLive()[i]);
		}
		
		
	}

	void print() {
		for (int i = 0; i < size.getY(); i++) {
			for (int j = 0; j < size.getX(); j++) {
				System.out.print(brd[i][j]);
			}
			System.out.println("endl");
		}
	}

	public void place_ship(Ship toinsert) {
		if (true) {
			for (int i = 0; i < toinsert.getHealth(); i++) {
				brd[toinsert.getStart().getY()][toinsert.getStart().getX()] = toinsert.getShipNumber();
			}
		} else {
			for (int i = 0; i < toinsert.getHealth(); i++) {
				brd[toinsert.getStart().getY()][toinsert.getStart().getX()] = toinsert.getShipNumber();
			}
			
		}
	}
	
	public boolean hit_loc(int x, int y) {
		if (brd[x][y] == -1) {//location empty
			brd[x][y] = -2;
			return false;// miss
		}
		if (brd[x][y] == -2) {//location already guessed or hit
			return false;//miss
		}
		if (brd[x][y] > -1) {//location occupied, ship number.
			brd[x][y] = -2;
			return true;
		}
		return false;
	}
	
	public void mark_loc(int x, int y) {
		//TODO damage ship#
		brd[x][y] = -1;// location damaged
	}
	
	
}
