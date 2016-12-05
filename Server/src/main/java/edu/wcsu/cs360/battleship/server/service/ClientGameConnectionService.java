package edu.wcsu.cs360.battleship.server.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;
import edu.wcsu.cs360.battleship.common.service.aop.IDispatcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

public class ClientGameConnectionService extends Thread {
	
	private Log log = LogFactory.getLog(this.getClass());
	private IDispatcher iDispatcher;
	private Socket socket;
	private Map<Socket, OutputStream> clientGameConnectionSocketList;
	private ObjectMapper objectMapper;
	
	private ClientGameConnectionService() {
		log.info("A client has connected to the server!");
	}
	
	public ClientGameConnectionService(Socket socket, Map<Socket, OutputStream> clientGameConnectionSocketList, IDispatcher iDispatcher) throws IOException {
		this();
		this.socket = socket;
		this.iDispatcher = iDispatcher;
		this.clientGameConnectionSocketList = clientGameConnectionSocketList;
		objectMapper = new ObjectMapper();
		objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, false);
		objectMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
		objectMapper.configure(SerializationFeature.CLOSE_CLOSEABLE, false);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		super.run();
		try {
			while (socket.isConnected()) {
				Request request = objectMapper.readValue(socket.getInputStream(), Request.class);
				Response response = iDispatcher.dispatch(request);
				if (response == null)
					response = new Response(200);
				log.info("Sending response: " + response.toString());
				for (Map.Entry<Socket, OutputStream> clientGameConnectionSocketEntry : clientGameConnectionSocketList.entrySet())
					objectMapper.writeValue(clientGameConnectionSocketEntry.getValue(), response);
			}
		} catch (IOException e) {
			log.error("Failed to process request!", e);
		} finally {
			try {
				if (socket.isConnected()) {
					socket.close();
					clientGameConnectionSocketList.remove(socket);
				}
			} catch (IOException e) {
				log.error("Unable to close socket!", e);
			}
		}
	}
	
}
