package edu.wcsu.cs360.battleship.server.service;

import edu.wcsu.cs360.battleship.common.service.io.IConnectionListenerService;
import edu.wcsu.cs360.battleship.common.service.aop.IDispatcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.net.Socket;

public class ClientConnectionHandlerService implements IConnectionListenerService {
	
	private Log log = LogFactory.getLog(this.getClass());
	private IDispatcher iDispatcher;
	
	private ClientConnectionHandlerService() {
		
	}
	
	public ClientConnectionHandlerService(IDispatcher iDispatcher) {
		this();
		this.iDispatcher = iDispatcher;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accept(Socket socket) throws IOException {
		ClientConnectionService clientConnectionService = new ClientConnectionService(socket, iDispatcher);
		clientConnectionService.start();
	}
	
}
