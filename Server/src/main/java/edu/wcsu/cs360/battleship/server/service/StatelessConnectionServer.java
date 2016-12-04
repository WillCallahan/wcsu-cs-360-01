package edu.wcsu.cs360.battleship.server.service;

import edu.wcsu.cs360.battleship.common.service.io.IConnectionListenerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;

public class StatelessConnectionServer implements IConnectionServer {

	private Log log = LogFactory.getLog(this.getClass());
	private ServerSocketFactory serverSocketFactory;
	private IConnectionListenerService iConnectionListenerService;

	private StatelessConnectionServer() {

	}

	public StatelessConnectionServer(ServerSocketFactory serverSocketFactory) {
		this.serverSocketFactory = serverSocketFactory;
	}

	public StatelessConnectionServer(IConnectionListenerService iConnectionListenerService) {
		this(ServerSocketFactory.getDefault());
		this.iConnectionListenerService = iConnectionListenerService;
	}

	public StatelessConnectionServer(ServerSocketFactory serverSocketFactory, IConnectionListenerService iConnectionListenerService) {
		this(serverSocketFactory);
		this.iConnectionListenerService = iConnectionListenerService;
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
				try {
					iConnectionListenerService.accept(serverSocket.accept());
				} catch (IOException e) {
					log.error("Failed to connect client!", e);
				}
				log.info("Successfully connected client!");
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
		run(8000);
	}
}
