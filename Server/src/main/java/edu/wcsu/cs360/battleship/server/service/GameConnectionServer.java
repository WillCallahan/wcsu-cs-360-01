package edu.wcsu.cs360.battleship.server.service;

import edu.wcsu.cs360.battleship.common.service.aop.IDispatcher;
import edu.wcsu.cs360.battleship.common.service.io.IConnectionListenerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;

public class GameConnectionServer extends Thread implements IConnectionServer {
	
	private final int MAX_CLIENTS = 2;
	private Log log = LogFactory.getLog(this.getClass());
	private ServerSocketFactory serverSocketFactory;
	private IDispatcher iDispatcher;
	
	private GameConnectionServer() {
		
	}
	
	private GameConnectionServer(ServerSocketFactory serverSocketFactory) {
		this.serverSocketFactory = serverSocketFactory;
	}
	
	public GameConnectionServer(ServerSocketFactory serverSocketFactory, IDispatcher iDispatcher) {
		this.serverSocketFactory = serverSocketFactory;
		this.iDispatcher = iDispatcher;
	}
	
	public GameConnectionServer(IDispatcher iDispatcher) {
		this(ServerSocketFactory.getDefault());
		this.iDispatcher = iDispatcher;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run(int port) {
		log.info("Attempting to listen for clients...");
		try (ServerSocket serverSocket = serverSocketFactory.createServerSocket(port)) {
			log.info("Successfully connected to port " + serverSocket.getLocalPort());
			while (true) {
				log.info("Listening for clients to start a new game...");
				IConnectionListenerService iConnectionListenerService = new ClientGameConnectionHandlerService(iDispatcher);
				for (int i = 0; i < MAX_CLIENTS; i++) {
					log.info("Waiting for client " + (i + 1) + " of " + MAX_CLIENTS);
					try {
						iConnectionListenerService.accept(serverSocket.accept());
					} catch (IOException e) {
						log.error("Failed to connect client!", e);
						i--;
					}
					log.info("Successfully connected client!");
				}
				log.info("Game started!");
			}
		} catch (IOException e) {
			log.error("Socket Error", e);
		}
		log.info("Exiting StatelessConnectionServer");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		run(8010);
	}
}
