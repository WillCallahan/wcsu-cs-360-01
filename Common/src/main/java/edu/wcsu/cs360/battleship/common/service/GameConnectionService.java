package edu.wcsu.cs360.battleship.common.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameConnectionService implements IConnectionService {
	
	private List<Client> clientList;
	private IDispatcher iDispatcher;
	private Log log = LogFactory.getLog(this.getClass());
	
	private GameConnectionService() {
		this.clientList = new ArrayList<>();
	}
	
	public GameConnectionService(IDispatcher iDispatcher) {
		this();
		this.iDispatcher = iDispatcher;
	}
	
	@Override
	public void accept(Socket socket) throws IOException {
		clientList.add(new Client(socket, iDispatcher));
		
	}
	
}
