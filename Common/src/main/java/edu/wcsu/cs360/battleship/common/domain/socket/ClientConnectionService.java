package edu.wcsu.cs360.battleship.common.domain.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.wcsu.cs360.battleship.common.service.IDispatcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnectionService extends Thread {
	
	private Log log = LogFactory.getLog(this.getClass());
	private IDispatcher iDispatcher;
	private Socket socket;
	private ObjectMapper objectMapper;
	private PrintWriter printWriter;
	
	private ClientConnectionService() {
		log.info("A client has connected to the server!");
	}
	
	public ClientConnectionService(Socket socket, IDispatcher iDispatcher) throws IOException {
		this();
		this.socket = socket;
		this.iDispatcher = iDispatcher;
		objectMapper = new ObjectMapper();
		printWriter = new PrintWriter(socket.getOutputStream(), true);
	}
	
	@Override
	public void run() {
		super.run();
		try {
			while (socket.isConnected()) {
				Request request = objectMapper.readValue(socket.getInputStream(), Request.class);
				Response response = iDispatcher.dispatch(request);
				objectMapper.writeValue(printWriter, response);
			}
		} catch (IOException e) {
			log.error("Failed to process request!", e);
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				log.error("Unable to close socket!", e);
			}
		}
		
	}
}
