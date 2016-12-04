package edu.wcsu.cs360.battleship.client.service.io;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Callable;

public class ServerConnectionService<T, U> implements Callable<U> {
	
	private Log log = LogFactory.getLog(this.getClass());
	private Socket socket;
	private ObjectMapper objectMapper;
	private Class<T> requestClass;
	private Class<U> responseClass;
	private T request;
	private U response;
	
	private ServerConnectionService() {
		socket = null;
		response = null;
		objectMapper = new ObjectMapper();
		objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, false);
		objectMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
		objectMapper.configure(SerializationFeature.CLOSE_CLOSEABLE, false);
	}
	
	public ServerConnectionService(Socket socket, T request, Class<T> requestClass, Class<U> responseClass) throws IOException {
		this();
		this.socket = socket;
		this.request = request;
		this.requestClass = requestClass;
		this.responseClass = responseClass;
	}
	
	public ServerConnectionService(String address, int port, T request, Class<T> requestClass, Class<U> responseClass) throws IOException {
		this();
		this.socket = new Socket(address, port);
		this.request = request;
		this.requestClass = requestClass;
		this.responseClass = responseClass;
	}
	
	public U call() {
		try {
			log.info("Making request: " + request.toString());
			objectMapper.writeValue(socket.getOutputStream(), request);
			U response = objectMapper.readValue(socket.getInputStream(), responseClass);
			log.info("Received response: " + response.toString());
			return response;
		} catch (IOException e) {
			throw new IllegalStateException("Unable to write to printWriter!", e);
		} finally {
			log.info("Closing socket");
			try {
				if (socket.isConnected())
					socket.close();
			} catch (IOException e) {
				throw new IllegalStateException("Unable to close socket!", e);
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
