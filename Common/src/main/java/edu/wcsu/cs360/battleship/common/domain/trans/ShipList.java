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
			if (liveShipList.get(i).dead()) {
				return false; // fleet is dead
			}
		}
		return true;
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
}
