package edu.wcsu.cs360.battleship.common.domain.trans;

import java.util.ArrayList;

public class Shiplist {

	ArrayList<Ship> live;
	
	boolean fleet_living() {
		for (int i = 0; i < live.size(); i++) {
			if (live.get(i).dead()) {
				return false; // fleet is dead
			}
		}
		return true;
	}
	
	Shiplist(){
		live = new ArrayList<Ship>();
	}
	
	void add_ship(Tuple start, Tuple end, byte shipnum){
		Ship tempship= new Ship(start, end,shipnum);
		live.add(tempship);
		
	}
	
	
	
	
	
	
	
	
	
	
}
