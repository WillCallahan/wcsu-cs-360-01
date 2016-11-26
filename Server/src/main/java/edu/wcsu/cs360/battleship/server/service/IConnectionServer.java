package edu.wcsu.cs360.battleship.server.service;

public interface IConnectionServer {
	
	/**
	 * Starts a Server that begins listening on the specified port
	 * @param port Port to listen to
	 */
	void run(int port);
	
	/**
	 * Starts a Server that begins listening on port 8000
	 *
	 * @see IConnectionServer#run(int)
	 */
	void run();
	
}
