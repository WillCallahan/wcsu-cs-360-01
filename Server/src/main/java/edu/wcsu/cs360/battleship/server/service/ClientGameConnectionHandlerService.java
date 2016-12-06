package edu.wcsu.cs360.battleship.server.service;

import edu.wcsu.cs360.battleship.common.service.aop.IDispatcher;
import edu.wcsu.cs360.battleship.common.service.io.IConnectionListenerService;
import edu.wcsu.cs360.battleship.server.domain.session.GameSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientGameConnectionHandlerService implements IConnectionListenerService {
	
	private Log log = LogFactory.getLog(this.getClass());
	private IDispatcher iDispatcher;
	private List<GameSession> gameSessionsList;
	
	private ClientGameConnectionHandlerService() {
		gameSessionsList = new ArrayList<>();
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
		GameSession gameSession = new GameSession(socket, null);
		gameSessionsList.add(gameSession);
		ClientGameConnectionService clientGameConnectionService = new ClientGameConnectionService(gameSession, gameSessionsList, iDispatcher);
		clientGameConnectionService.start();
	}
	
}
