package edu.wcsu.cs360.battleship.domain.dmo.local;

public class Ship {
	private Tuple start, end;
	private byte shipNumber;
	private int health;
	
	public Ship(Tuple start, Tuple end) {
		this.start = start;
		this.end = end;
		health = start.size(end);// health is implied on the size of the ship
	}
	
	public boolean vertical() {
		if (start.getX() == end.getX()) {
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

	public byte getShipNumber() {
		return shipNumber;
	}

	public void setShipNumber(byte shipNumber) {
		this.shipNumber = shipNumber;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
