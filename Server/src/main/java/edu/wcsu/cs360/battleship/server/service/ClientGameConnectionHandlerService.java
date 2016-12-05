package edu.wcsu.cs360.battleship.server.service;

import edu.wcsu.cs360.battleship.common.service.aop.IDispatcher;
import edu.wcsu.cs360.battleship.common.service.io.IConnectionListenerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ClientGameConnectionHandlerService implements IConnectionListenerService {
	
	private Log log = LogFactory.getLog(this.getClass());
	private IDispatcher iDispatcher;
	private Map<Socket, OutputStream> clientGameConnectionSocketMap;
	
	private ClientGameConnectionHandlerService() {
		clientGameConnectionSocketMap = new HashMap<>();
	}
	
	public ClientGameConnectionHandlerService(IDispatcher iDispatcher) {
		this();
		this.iDispatcher = iDispatcher;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accept(Socket socket) throws IOException {
		clientGameConnectionSocketMap.put(socket, socket.getOutputStream());
		ClientGameConnectionService clientGameConnectionService = new ClientGameConnectionService(socket, clientGameConnectionSocketMap, iDispatcher);
		clientGameConnectionService.start();
	}
	
}
