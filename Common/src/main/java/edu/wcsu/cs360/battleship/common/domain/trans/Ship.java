package edu.wcsu.cs360.battleship.common.domain.trans;

public class Ship {
	Tuple start, end;
	public byte shipnum;
	int health;
	
	Ship(Tuple start, Tuple end, byte num){
			shipnum=num;
			this.start=start;
			this.end=end;
		
		health=start.size(end);// health is implied on the size of the ship
	}
	public boolean dead(){
		if(health==0){
			return true;
		}
		return false;
	}
	
	public boolean vertical(){
		if(start.x==end.x){
			return true;
		}else{
			return false;
		}	
	}
	
	public boolean damage(){//damage this ship
		health=health--;
		if(health==0){
			return false; //ship has died
		}
		return true;// ship is living
	}
}
