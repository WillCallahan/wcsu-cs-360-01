package edu.wcsu.cs360.battleship.client.service.io;

import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.Socket;

public class GameConnectionHandlerService {
	
	private Log log = LogFactory.getLog(this.getClass());
	private Socket socket;
	private String address;
	private int port;
	
	private GameConnectionHandlerService() {
		log.info("Starting a new GameConnectionHandlerService");
	}
	
	public GameConnectionHandlerService(String address, int port) {
		this();
		log.info("Assigning GameConnectionHandlerService to address " + address + " amd port " + port);
		this.address = address;
		this.port = port;
	}
	
	@PreDestroy
	public void preDestroy() throws IOException {
		log.info("Stopping game connection...");
		if (socket != null && socket.isConnected())
			socket.close();
		else
			log.debug("Game connection already closed!");
	}
	
	public void initialize() throws IOException {
		if (socket == null || socket.isClosed()) {
			log.info("Starting new game connection...");
			socket = new Socket(address, port);
		}
	}
	
	public void listen(IGameConnectionCallback<Response> iGameConnectionCallback) throws IOException {
		if (socket == null || socket.isClosed())
			throw new IllegalStateException("Cannot connect to socket; was the GameConnectionHandlerService#initialize called?");
		GameConnectionReaderService<Response> gameConnectionReaderService = new GameConnectionReaderService<>(socket, iGameConnectionCallback, Response.class);
		gameConnectionReaderService.start();
	}
	
	public void send(Request request) throws IOException {
		if (socket == null || socket.isClosed())
			throw new IllegalStateException("Cannot connect to socket; was the GameConnectionHandlerService#initialize called?");
		GameConnectionWriterService<Request> gameConnectionWriterService = new GameConnectionWriterService<>(socket, request);
		gameConnectionWriterService.start();
	}
	
}
