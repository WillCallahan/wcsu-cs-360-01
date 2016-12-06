package edu.wcsu.cs360.battleship.client.service.io;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class GameConnectionWriterService<T> extends Thread {
	
	private Log log = LogFactory.getLog(this.getClass());
	private ObjectMapper objectMapper;
	private OutputStream outputStream;
	private T request;
	
	private GameConnectionWriterService() {
		outputStream = null;
		objectMapper = new ObjectMapper();
		objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, false);
		objectMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
		objectMapper.configure(SerializationFeature.CLOSE_CLOSEABLE, false);
	}
	
	public GameConnectionWriterService(Socket socket, T request) throws IOException {
		this();
		outputStream = socket.getOutputStream();
		this.request = request;
	}
	
	public GameConnectionWriterService(OutputStream outputStream, T request) {
		this();
		this.outputStream = outputStream;
		this.request = request;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		try {
			log.info("Making request: " + request.toString());
			objectMapper.writeValue(outputStream, request);
		} catch (IOException e) {
			throw new IllegalStateException("Unable to write to outputStream!", e);
		} finally {
			log.info("Closing socket");
		}
	}
	
}
