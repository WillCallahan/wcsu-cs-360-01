package edu.wcsu.cs360.battleship.server.service;

import edu.wcsu.cs360.battleship.common.service.GameConnectionService;
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
		try (ServerSocket serverSocket = serverSocketFactory.createServerSocket(port)) {
			while (true) {
				IConnectionService iConnectionService = new GameConnectionService(iDispatcher);
				iConnectionService.accept(serverSocket.accept());
			}
		} catch (IOException e) {
			log.error("Socket Error", e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		run(8000);
	}
}
