package edu.wcsu.cs360.battleship.client.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.net.Socket;

public class ServerConnectionService<T, U> extends Thread {
	
	private Log log = LogFactory.getLog(this.getClass());
	private Socket socket;
	private ObjectMapper objectMapper;
	private T request;
	private U response;
	
	private ServerConnectionService() {
		socket = null;
		response = null;
		request = null;
		objectMapper = new ObjectMapper();
		objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, false);
		objectMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
		objectMapper.configure(SerializationFeature.CLOSE_CLOSEABLE, false);
	}
	
	public ServerConnectionService(Socket socket, T request) throws IOException {
		this();
		this.socket = socket;
		this.request = request;
	}
	
	public ServerConnectionService(String address, int port, T request) throws IOException {
		this();
		this.socket = new Socket(address, port);
		this.request = request;
	}
	
	public void run() {
		try {
			log.info("Making request: " + request.toString());
			objectMapper.writeValue(socket.getOutputStream(), request);
			Response response = objectMapper.readValue(socket.getInputStream(), Response.class);
			log.info("Received response: " + response.toString());
		} catch (IOException e) {
			log.error("Unable to write to printWriter!", e);
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
	
	public void setRequest(T request) {
		this.request = request;
	}
	
	public U getResponse() {
		return response;
	}
}
