package edu.wcsu.cs360.battleship.common.domain.trans;

public class Tuple {
	int x;
	int y;
	
	public Tuple(int i, int j) {
		x=i;
		y=j;
	}

	public int size(Tuple p2){
		if(x==p2.x){
			return Math.abs(y-p2.y);
		}else{
			return Math.abs(x-p2.x);
		}
	}

	
	
	
	
	
	
	
	
	
}
