package edu.wcsu.cs360.battleship.client.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnectionService extends Thread {
	
	private Log log = LogFactory.getLog(this.getClass());
	private Socket socket;
	private ObjectMapper objectMapper;
	private PrintWriter printWriter;
	
	private ServerConnectionService() {
		socket = null;
		objectMapper = new ObjectMapper();
	}
	
	public ServerConnectionService(Socket socket) throws IOException {
		this();
		this.socket = socket;
		printWriter = new PrintWriter(socket.getOutputStream(), true);
	}
	
	public ServerConnectionService(String address, int port) throws IOException {
		this();
		this.socket = new Socket(address, port);
		printWriter = new PrintWriter(socket.getOutputStream(), true);
	}
	
	@Override
	public void run() {
		Request request = new Request();
		request.setContentType("application/json");
		request.setBody("Sample");
		request.setTarget("gameController.getTest");
		try {
			objectMapper.writeValue(printWriter, request);
			//Response response = objectMapper.readValue(socket.getInputStream(), Response.class);
			//log.info("Received response: " + response.toString());
		} catch (IOException e) {
			log.error("Unable to write to printWriter!", e);
		}
	}
	
}
