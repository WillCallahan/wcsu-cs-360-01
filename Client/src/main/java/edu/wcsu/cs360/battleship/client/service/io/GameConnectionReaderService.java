package edu.wcsu.cs360.battleship.client.service.io;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class GameConnectionReaderService<T> extends Thread {
	
	private Log log = LogFactory.getLog(this.getClass());
	private ObjectMapper objectMapper;
	private InputStream inputStream;
	private IGameConnectionCallback<Response> iGameConnectionCallback;
	
	private GameConnectionReaderService() {
		inputStream = null;
		objectMapper = new ObjectMapper();
		objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, false);
		objectMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
		objectMapper.configure(SerializationFeature.CLOSE_CLOSEABLE, false);
	}
	
	public GameConnectionReaderService(Socket socket, IGameConnectionCallback<Response> iGameConnectionCallback) throws IOException {
		this();
		inputStream = socket.getInputStream();
		this.iGameConnectionCallback = iGameConnectionCallback;
	}
	
	public GameConnectionReaderService(InputStream inputStream, IGameConnectionCallback<Response> iGameConnectionCallback) {
		this();
		this.inputStream = inputStream;
		this.iGameConnectionCallback = iGameConnectionCallback;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Response response = objectMapper.readValue(inputStream, Response.class);
				iGameConnectionCallback.callback(response);
			} catch (IOException e) {
				log.error("Failed to process response!", e);
			}
		}
	}
	
}
