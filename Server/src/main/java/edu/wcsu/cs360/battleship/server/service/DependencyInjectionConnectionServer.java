package edu.wcsu.cs360.battleship.server.service;

import edu.wcsu.cs360.battleship.common.service.IConnectionService;
import edu.wcsu.cs360.battleship.common.service.IDispatcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;

public class DependencyInjectionConnectionServer implements IConnectionServer {
	
	private Log log = LogFactory.getLog(this.getClass());
	private ServerSocketFactory serverSocketFactory;
	private IDispatcher iDispatcher;
	private static final int MAX_CLIENTS = 2;
	
	private DependencyInjectionConnectionServer() {
		
	}
	
	public DependencyInjectionConnectionServer(ServerSocketFactory serverSocketFactory) {
		this.serverSocketFactory = serverSocketFactory;
	}
	
	public DependencyInjectionConnectionServer(IDispatcher iDispatcher) {
		this(ServerSocketFactory.getDefault());
		this.iDispatcher = iDispatcher;
	}
	
	public DependencyInjectionConnectionServer(ServerSocketFactory serverSocketFactory, IDispatcher iDispatcher) {
		this(serverSocketFactory);
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
				IConnectionService iConnectionService = new ClientConnectionService(iDispatcher);
				for (int i = 0; i < MAX_CLIENTS; i++) {
					log.info("Waiting for client " + (i + 1) + " of " + MAX_CLIENTS);
					try {
						iConnectionService.accept(serverSocket.accept());
					} catch (IOException e) {
						log.error("Failed to connect client " + (i + 1) + " of " + MAX_CLIENTS, e);
					}
				}
				log.info("Game Started!");
			}
		} catch (IOException e) {
			log.error("Socket Error", e);
		}
		log.info("Exiting DependencyInjectionConnectionServer");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		run(8000);
	}
}
