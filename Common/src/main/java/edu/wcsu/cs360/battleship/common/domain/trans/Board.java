package edu.wcsu.cs360.battleship.common.domain.trans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Board {
	
	public static final int MAX_SHIPS = 10;
	
	@JsonIgnore
	private Log log = LogFactory.getLog(this.getClass());
	
	private Tuple size;
	
	private boolean badTable = false;
	
	private byte[][] board;
	
	private ShipList shipList;
	
	private Board() {
		
	}
	
	public Board(Tuple size) {
		this.size = size;
		board = new byte[size.getX()][size.getY()];
		shipList = new ShipList();

//		shipList.addShip(new Tuple(2, 2), new Tuple(2, 3), (byte) 0);
//		shipList.addShip(new Tuple(1, 3), new Tuple(3, 3), (byte) 1);
//
//		//System.out.println(shipList.liveShipList.size());
//		for (int i = 0; i < shipList.getLiveShipList().size(); i++) {
//			placeShip(shipList.getLiveShipList().get(i));
//		}
	}
	
	void print() {
		System.out.print("R " + "0 " + "1 " + "2 " + "3 " + "4 " + "\n");//row numbers
		for (int i = 0; i < size.getY(); i++) {
			System.out.print(i);
			for (int j = 0; j < size.getY(); j++) {
				System.out.print(" ");// spacing for readability
				System.out.print(board[i][j]);
			}
			System.out.println(" //");
		}
	}
	
	public void placeShip(Ship toInsert) {
//		if (toInsert.getStart().getX() == toInsert.getEnd().getX()) {
//			for (int i = 0; i <= toInsert.getHealth(); i++) {
//				if (board[toInsert.getEnd().getX() + i][toInsert.getEnd().getY()] != 0) {
//					log.error("Ships overlapping!");
//					badTable = true; // this table should disqualify player
//				}
//				board[toInsert.getEnd().getX() + i][toInsert.getEnd().getY()] = (byte) (toInsert.getShipNum() + 2);
//			}
//		} else {
//			for (int i = 0; i <= toInsert.getHealth(); i++) {
//				if (board[toInsert.getEnd().getX()][toInsert.getEnd().getY() + i] != 0) {
//					log.error("Ships overlapping!");
//					badTable = true; // this table should disqualify player
//				}
//				board[toInsert.getEnd().getX()][toInsert.getEnd().getY() + i] = (byte) (toInsert.getShipNum() + 2);
//
//			}
//
//		}
		board[toInsert.getStart().getX()][toInsert.getStart().getY()] = (byte) (toInsert.getShipNum() + 2);  // Sorry dude, not enough time
	}
	
	public boolean hitLocation(int x, int y) {
		if (board[x][y] == 0) {//location empty
			board[x][y] = 1;// missed guess
			return false;// missed guess
		}
		
		if (board[x][y] == 1) {//location already guessed or hit
			return false;//miss
		}
		
		if (board[x][y] > 1) {//location occupied, ship number= loc number-2.
			shipList.getByStartTuple(new Tuple(x, y)).damage();//decrement ship health by 1.
			board[x][y] = 1;
			
			return true;// hit guess
		}
		return false;
	}
	
	public boolean isMissOrHitLocation(int x, int y) {
		return board[x][y] == 1;
	}
	
	public boolean isBadTable() {
		return badTable;
	}
	
	public void setBadTable(boolean badTable) {
		this.badTable = badTable;
	}
	
	public Tuple getSize() {
		return size;
	}
	
	public ShipList getShipList() {
		return shipList;
	}
	
	public void setShipList(ShipList shipList) {
		this.shipList = shipList;
	}
	
	public final byte[][] getBoard() {
		return board;
	}
}
