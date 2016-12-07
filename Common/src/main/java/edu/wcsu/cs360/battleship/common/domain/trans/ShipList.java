package edu.wcsu.cs360.battleship.common.domain.trans;

import java.util.ArrayList;
import java.util.List;

public class ShipList {
	
	private List<Ship> liveShipList;
	
	public ShipList() {
		liveShipList = new ArrayList<>();
	}
	
	public boolean fleetLiving() {
		for (int i = 0; i < liveShipList.size(); i++) {
			if (!liveShipList.get(i).dead()) {
				return true; // fleet is not dead
			}
		}
		return false;
	}
	
	public void addShip(Tuple start, Tuple end, byte shipNum) {
		Ship tempShip = new Ship(start, end, shipNum);
		liveShipList.add(tempShip);
	}
	
	public List<Ship> getLiveShipList() {
		return liveShipList;
	}
	
	public void setLiveShipList(List<Ship> liveShipList) {
		this.liveShipList = liveShipList;
	}
	
	public Ship getByStartTuple(Tuple tuple) {
		for (Ship ship : liveShipList) {
			if (ship.getStart().getX() == tuple.getX() && ship.getStart().getY() == tuple.getY())
				return ship;
		}
		return null;
	}
}
