package edu.wcsu.cs360.battleship.common.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;


public class Client extends Thread {
	
	private Log log = LogFactory.getLog(this.getClass());
	private IDispatcher iDispatcher;
	private Socket socket;
	private ObjectMapper objectMapper;
	private OutputStream outputStream;
	
	private Client() {
		log.info("A client has connected to the server!");
	}
	
	public Client(Socket socket, IDispatcher iDispatcher) throws IOException {
		this();
		this.socket = socket;
		this.iDispatcher = iDispatcher;
		objectMapper = new ObjectMapper();
		outputStream = socket.getOutputStream();
	}
	
	@Override
	public void run() {
		super.run();
		try {
			Request request = objectMapper.readValue(socket.getInputStream(), Request.class);
			objectMapper.writeValue(outputStream, iDispatcher.dispatch(request));
		} catch (IOException e) {
			log.error("Failed to read request!", e);
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				log.error("Unable to close socket!", e);
			}
		}
		
	}
}
