package edu.wcsu.cs360.battleship.common.domain.trans;

public class Ship {
	private Tuple start, end;
	private byte shipNum;
	private int health;
	
	private Ship() {
		
	}
	
	public Ship(Tuple start, Tuple end, byte num) {
		shipNum = num;
		this.start = start;
		this.end = end;
		
		health = start.size(end) + 1;// health is implied on the size of the ship
	}
	
	public boolean dead() {
		if (health == 0) {
			return true;
		}
		return false;
	}
	
	public boolean vertical() {
		if (start.getX() == end.getY()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean damage() {//damage this ship
		health = health--;
		if (health == 0) {
			return false; //ship has died
		}
		return true;// ship is living
	}
	
	public Tuple getStart() {
		return start;
	}
	
	public void setStart(Tuple start) {
		this.start = start;
	}
	
	public Tuple getEnd() {
		return end;
	}
	
	public void setEnd(Tuple end) {
		this.end = end;
	}
	
	public byte getShipNum() {
		return shipNum;
	}
	
	public void setShipNum(byte shipNum) {
		this.shipNum = shipNum;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
}
