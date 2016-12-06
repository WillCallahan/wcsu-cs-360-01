package edu.wcsu.cs360.battleship.server.service;

import edu.wcsu.cs360.battleship.common.service.aop.IDispatcher;
import edu.wcsu.cs360.battleship.common.service.io.IConnectionListenerService;
import edu.wcsu.cs360.battleship.server.domain.session.PlayerSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientGameConnectionHandlerService implements IConnectionListenerService {
	
	private Log log = LogFactory.getLog(this.getClass());
	private IDispatcher iDispatcher;
	private List<PlayerSession> playerSessionsList;
	
	private ClientGameConnectionHandlerService() {
		playerSessionsList = new ArrayList<>();
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
		PlayerSession playerSession = new PlayerSession(socket, null);
		playerSessionsList.add(playerSession);
		ClientGameConnectionService clientGameConnectionService = new ClientGameConnectionService(playerSession, playerSessionsList, iDispatcher);
		clientGameConnectionService.start();
	}
	
}
