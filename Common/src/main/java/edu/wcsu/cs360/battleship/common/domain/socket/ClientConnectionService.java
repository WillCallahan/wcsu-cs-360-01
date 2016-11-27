package edu.wcsu.cs360.battleship.common.domain.socket;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.wcsu.cs360.battleship.common.service.IDispatcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.net.Socket;

public class ClientConnectionService extends Thread {
	
	private Log log = LogFactory.getLog(this.getClass());
	private IDispatcher iDispatcher;
	private Socket socket;
	private ObjectMapper objectMapper;
	
	private ClientConnectionService() {
		log.info("A client has connected to the server!");
	}
	
	public ClientConnectionService(Socket socket, IDispatcher iDispatcher) throws IOException {
		this();
		this.socket = socket;
		this.iDispatcher = iDispatcher;
		objectMapper = new ObjectMapper();
		objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, false);
		objectMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
		objectMapper.configure(SerializationFeature.CLOSE_CLOSEABLE, false);
	}
	
	@Override
	public void run() {
		super.run();
		try {
			Request request = objectMapper.readValue(socket.getInputStream(), Request.class);
			log.info("Received request: " + request.toString());
			Response response = iDispatcher.dispatch(request);
			if (response == null)
				response = new Response(200);
			log.info("Sending response: " + response.toString());
			objectMapper.writeValue(socket.getOutputStream(), response);
		} catch (IOException e) {
			log.error("Failed to process request!", e);
		} finally {
			log.info("Closing socket");
			try {
				if (socket.isConnected())
					socket.close();
			} catch (IOException e) {
				log.error("Unable to close socket!", e);
			}
		}
		
	}
}
