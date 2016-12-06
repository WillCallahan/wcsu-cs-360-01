package edu.wcsu.cs360.battleship.server.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;
import edu.wcsu.cs360.battleship.common.domain.trans.Game;
import edu.wcsu.cs360.battleship.common.domain.trans.Player;
import edu.wcsu.cs360.battleship.common.service.aop.IDispatcher;
import edu.wcsu.cs360.battleship.server.domain.session.GameSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class ClientGameConnectionService extends Thread {
	
	private Log log = LogFactory.getLog(this.getClass());
	private IDispatcher iDispatcher;
	private GameSession gameSession;
	private List<GameSession> gameSessionList;
	private ObjectMapper objectMapper;
	
	private ClientGameConnectionService() {
		log.info("A client has connected to the server!");
	}
	
	public ClientGameConnectionService(GameSession gameSession, List<GameSession> gameSessionList, IDispatcher iDispatcher) throws IOException {
		this();
		this.gameSession = gameSession;
		this.iDispatcher = iDispatcher;
		this.gameSessionList = gameSessionList;
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
			while (gameSession.getSocket().isConnected()) {
				Request request = objectMapper.readValue(gameSession.getInputStream(), Request.class);
				Response response = iDispatcher.dispatch(request);
				if (response == null)
					response = new Response(200);
				log.info("Sending response: " + response.toString());
				for (GameSession gameSession : gameSessionList)
					objectMapper.writeValue(gameSession.getOutputStream(), response);
			}
		} catch (IOException e) {
			log.error("Failed to process request!", e);
		} finally {
			try {
				if (gameSession.getSocket().isConnected()) {
					gameSession.getSocket().close();
					gameSessionList.remove(gameSession);
				}
			} catch (IOException e) {
				log.error("Unable to close socket!", e);
			}
		}
	}
	
}
