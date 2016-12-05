package edu.wcsu.cs360.battleship.common.domain.trans;

public class Board {
	
	public static final int MAX_SHIPS = 10;
	
	private Tuple size;
	
	private boolean badTable = false;
	
	private byte[][] brd;
	
	private ShipList shl;
	
	public Board() {
		size = new Tuple(5, 5);
		brd = new byte[size.getX()][size.getY()];
		shl = new ShipList();
		
		shl.add_ship(new Tuple(2, 2), new Tuple(2, 3), (byte) 0);
		shl.add_ship(new Tuple(1, 3), new Tuple(3, 3), (byte) 1);
		
		//System.out.println(shl.live.size());
		for (int i = 0; i < shl.live.size(); i++) {
			place_ship(shl.live.get(i));
		}
	}
	
	void print() {
		System.out.print("R " + "0 " + "1 " + "2 " + "3 " + "4 " + "\n");//row numbers
		for (int i = 0; i < size.getY(); i++) {
			System.out.print(i);
			for (int j = 0; j < size.getY(); j++) {
				System.out.print(" ");// spacing for readability
				System.out.print(brd[i][j]);
			}
			System.out.println(" //");
		}
	}
	
	public void place_ship(Ship toInsert) {
		if (toInsert.getEnd().getX() == toInsert.getEnd().getX()) {
			for (int i = 0; i <= toInsert.getHealth(); i++) {
				if (brd[toInsert.getEnd().getY() + i][toInsert.getEnd().getX()] != 0) {
					System.out.print("error, ships overlapping\n");
					badTable = true; // this table should disqualify player
				}
				brd[toInsert.getEnd().getY() + i][toInsert.getEnd().getX()] = (byte) (toInsert.getShipNum() + 2);
			}
		} else {
			for (int i = 0; i <= toInsert.getHealth(); i++) {
				if (brd[toInsert.getEnd().getY()][toInsert.getEnd().getX() + i] != 0) {
					System.out.print("error, ships overlapping\n");
					badTable = true; // this table should disqualify player
				}
				brd[toInsert.getEnd().getY()][toInsert.getEnd().getX() + i] = (byte) (toInsert.getShipNum() + 2);
				
			}
			
		}
	}
	
	public boolean hitLocation(int x, int y) {
		if (brd[x][y] == 0) {//location empty
			brd[x][y] = 1;// missed guess
			return false;// missed guess
		}
		
		if (brd[x][y] == 1) {//location already guessed or hit
			return false;//miss
		}
		
		if (brd[x][y] > 1) {//location occupied, ship number= loc number-2.
			shl.live.get(brd[x][y] - 2).damage();//decrement ship health by 1.
			brd[x][y] = 1;
			
			return true;// hit guess
		}
		return false;
	}
	
	public boolean isBadTable() {
		return badTable;
	}
	
	public void setBadTable(boolean badTable) {
		this.badTable = badTable;
	}
}
