package edu.wcsu.cs360.battleship.common.domain.trans;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class PlayerTest {
	
	private Log log = LogFactory.getLog(this.getClass());
	private Player bill = new Player(5);
	
	
	/**
	 * This is just like a main class; white your tests in here and click the play button to the left of the method
	 * to run it. If an exception occurs, it is easier to debug
	 */
	@Test
	public void hit_locTest() {
		bill.getBoard().print();
		bill.getBoard().hitLocation(3, 3);
		/*
		 * Use String.format("%n") for a new line; Windows uses "\r\n" for a new line while Linux uses "\n"
		 * String.format("%n") will determine which one to use based on the operating system. Alternatively, you
		 * can ue System.getProperty("line.separator"); for a new line
		 */
		log.info(String.format("%n a location was damaged%n"));
		bill.getBoard().print();
	}
	
	@Test
	public void hit_locTestException() throws Exception {
		throw new Exception("This should throw an exception and fail the test");
	}
	
	@Test(expected = Exception.class)
	public void hit_locTestExceptionPass() throws Exception {
		throw new Exception("This should throw an exception, but the test annotation will expect it. This is actually passing the test");
	}
	
}
