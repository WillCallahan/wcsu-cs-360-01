package edu.wcsu.cs360.battleship.common.domain.trans;

public class Board {
	private Tuple size;
	
	public boolean bad_table=false;
	
	byte[][] brd;
	
	Shiplist shl;
	
	Board(){
		size=new Tuple(5,5);
		brd=new byte[size.x][size.y];
		shl=new Shiplist();
		
		shl.add_ship(new Tuple(2,2), new Tuple(2,3), (byte)0);
		shl.add_ship(new Tuple(1,3), new Tuple(3,3), (byte)1);
		
		//System.out.println(shl.live.size());
		for(int i=0;i<shl.live.size();i++){
			place_ship(shl.live.get(i));
		}
		
	}

	void print(){
		System.out.print("R " +"0 "+"1 "+"2 "+"3 "+"4 "+"\n");//row numbers
		for(int i=0;i<size.y;i++){
			System.out.print(i);
			for(int j=0;j<size.x;j++){
				System.out.print(" ");// spacing for readability
				System.out.print(brd[i][j]);
			}
			System.out.println(" //");
		}
	}
	public void place_ship(Ship toinsert){
		if(toinsert.start.x==toinsert.end.x){
			for(int i=0;i<=toinsert.health;i++){
				if(brd[toinsert.start.y+i][toinsert.start.x]!=0){
					System.out.print("error, ships overlapping\n");
					bad_table=true; // this table should disqualify player
				}
				brd[toinsert.start.y+i][toinsert.start.x]=(byte) (toinsert.shipnum+2);
			}
		}else{
			for(int i=0;i<=toinsert.health;i++){
				if(brd[toinsert.start.y][toinsert.start.x+i]!=0){
					System.out.print("error, ships overlapping\n");
					bad_table=true; // this table should disqualify player
				}
				brd[toinsert.start.y][toinsert.start.x+i]=(byte) (toinsert.shipnum+2);
				
			}
			
		}
	}
	
	public boolean hit_loc(int x,int y){
		if(brd[x][y]==0){//location empty
			brd[x][y]=1;// missed guess
			return false;// missed guess
		}
		
		if(brd[x][y]==1){//location already guessed or hit
			return false;//miss
		}
		
		if(brd[x][y]>1){//location occupied, ship number= loc number-2.
			shl.live.get(brd[x][y]-2).damage();//decrement ship health by 1.
			brd[x][y]=1;
			
			return true;// hit guess
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
