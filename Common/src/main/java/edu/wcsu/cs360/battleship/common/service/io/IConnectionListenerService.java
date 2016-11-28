package edu.wcsu.cs360.battleship.common.service.io;

import java.io.IOException;
import java.net.Socket;

public interface IConnectionListenerService {
	
	void accept(Socket socket) throws IOException;
	
}
