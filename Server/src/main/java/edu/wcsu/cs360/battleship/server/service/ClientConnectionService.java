package edu.wcsu.cs360.battleship.server.service;

import edu.wcsu.cs360.battleship.common.domain.socket.ClientConnection;
import edu.wcsu.cs360.battleship.common.service.IConnectionService;
import edu.wcsu.cs360.battleship.common.service.IDispatcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientConnectionService implements IConnectionService {
	
	private List<ClientConnection> clientConnectionList;
	private IDispatcher iDispatcher;
	private Log log = LogFactory.getLog(this.getClass());
	
	private ClientConnectionService() {
		this.clientConnectionList = new ArrayList<>();
	}
	
	public ClientConnectionService(IDispatcher iDispatcher) {
		this();
		this.iDispatcher = iDispatcher;
	}
	
	@Override
	public void accept(Socket socket) throws IOException {
		ClientConnection clientConnection = new ClientConnection(socket, iDispatcher);
		clientConnectionList.add(clientConnection);
		clientConnection.start();
	}
	
}
