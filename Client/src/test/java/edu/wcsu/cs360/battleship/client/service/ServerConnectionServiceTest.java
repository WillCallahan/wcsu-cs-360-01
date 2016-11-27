package edu.wcsu.cs360.battleship.client.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ServerConnectionServiceTest {
	
	private ServerConnectionService serverConnectionService;
	
	@Before
	public void setUp() throws Exception {
		serverConnectionService = new ServerConnectionService("localhost", 8000);
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void run() throws InterruptedException {
		
	}
	
}
