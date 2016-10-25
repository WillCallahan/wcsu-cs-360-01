package edu.wcsu.cs360.battleship.domain.dmo.local;

public class ShipList {

	private Ship[] live;
	private byte liveCount;
	
	public ShipList(byte size) {
		liveCount = size;
		live = new Ship[size];
	}
	
	void add_ship(Tuple start, Tuple end, int shipNumber) {
		live[shipNumber] = new Ship(start, end);
		
	}

	public Ship[] getLive() {
		return live;
	}

	public void setLive(Ship[] live) {
		this.live = live;
	}

	public byte getLiveCount() {
		return liveCount;
	}

	public void setLiveCount(byte liveCount) {
		this.liveCount = liveCount;
	}
}
