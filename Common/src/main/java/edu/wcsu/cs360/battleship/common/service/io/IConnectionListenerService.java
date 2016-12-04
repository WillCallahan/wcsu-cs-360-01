package edu.wcsu.cs360.battleship.common.service.io;

import java.io.IOException;
import java.net.Socket;

public interface IConnectionListenerService {
	
	/**
	 * Accepts a connection on a {@link Socket}
	 * @param socket Socket to connect to
	 * @throws IOException If an error occurs during socket communication
	 */
	void accept(Socket socket) throws IOException;
	
}
