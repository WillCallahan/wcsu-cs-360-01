package edu.wcsu.cs360.battleship.common.domain.trans;

public class Main {

	public static void main(String[] args) {
		
		Player bill= new Player(5);
		bill.usrboard.print();
		bill.usrboard.hit_loc(3, 3);
		System.out.print("\n a location was damaged\n");
		bill.usrboard.print();
	}

}
