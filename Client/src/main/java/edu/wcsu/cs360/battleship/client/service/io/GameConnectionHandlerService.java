package edu.wcsu.cs360.battleship.client.service.io;

import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
	
	public void stop() throws IOException {
		if (socket != null && socket.isConnected()) {
			log.info("Stopping game connection...");
			socket.close();
		}
		else
			log.warn("Cannot stop game connection; connection already closed!");
	}
	
	public void start(IGameConnectionCallback<Response> iGameConnectionCallback) throws IOException {
		if (socket == null) {
			log.info("Starting new game connection...");
			socket = new Socket(address, port);
			receive(iGameConnectionCallback);
		}
		else
			log.warn("Cannot start new game connection; game already connceted!");
	}
	
	private void receive(IGameConnectionCallback<Response> iGameConnectionCallback) throws IOException {
		GameConnectionReaderService<Response> gameConnectionReaderService = new GameConnectionReaderService<>(socket, iGameConnectionCallback, Response.class);
		gameConnectionReaderService.start();
	}
	
	public void send(Request request) throws IOException {
		GameConnectionWriterService<Request> gameConnectionWriterService = new GameConnectionWriterService<>(socket, request);
		gameConnectionWriterService.start();
	}
	
}
