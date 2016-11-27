package edu.wcsu.cs360.battleship.server.service;

import edu.wcsu.cs360.battleship.common.domain.socket.ClientConnectionService;
import edu.wcsu.cs360.battleship.common.service.IConnectionService;
import edu.wcsu.cs360.battleship.common.service.IDispatcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientConnectionHandlerService implements IConnectionService {
	
	private List<ClientConnectionService> clientConnectionServiceList;
	private IDispatcher iDispatcher;
	private Log log = LogFactory.getLog(this.getClass());
	
	private ClientConnectionHandlerService() {
		this.clientConnectionServiceList = new ArrayList<>();
	}
	
	public ClientConnectionHandlerService(IDispatcher iDispatcher) {
		this();
		this.iDispatcher = iDispatcher;
	}
	
	@Override
	public void accept(Socket socket) throws IOException {
		ClientConnectionService clientConnectionService = new ClientConnectionService(socket, iDispatcher);
		clientConnectionServiceList.add(clientConnectionService);
		clientConnectionService.start();
	}
	
}
