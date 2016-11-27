package edu.wcsu.cs360.battleship.common.service;

import java.io.IOException;
import java.net.Socket;

public interface IConnectionListenerService {
	
	void accept(Socket socket) throws IOException;
	
}
