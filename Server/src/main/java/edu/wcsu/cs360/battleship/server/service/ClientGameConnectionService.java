package edu.wcsu.cs360.battleship.server.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;
import edu.wcsu.cs360.battleship.common.domain.trans.Game;
import edu.wcsu.cs360.battleship.common.service.aop.IDispatcher;
import edu.wcsu.cs360.battleship.server.domain.session.PlayerSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.List;

public class ClientGameConnectionService extends Thread {
	
	private Log log = LogFactory.getLog(this.getClass());
	private IDispatcher iDispatcher;
	private PlayerSession playerSession;
	private Game game;
	private List<PlayerSession> playerSessionList;
	private ObjectMapper objectMapper;
	
	private ClientGameConnectionService() {
		log.info("A client has connected to the server!");
	}
	
	public ClientGameConnectionService(PlayerSession playerSession, Game game, List<PlayerSession> playerSessionList, IDispatcher iDispatcher) throws IOException {
		this();
		this.playerSession = playerSession;
		this.iDispatcher = iDispatcher;
		this.playerSessionList = playerSessionList;
		this.game = game;
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
			while (playerSession.getSocket().isConnected()) {
				Request request = objectMapper.readValue(playerSession.getInputStream(), Request.class);
				Response response = iDispatcher.dispatch(request, playerSessionList, playerSession, game);
				if (response == null)
					response = new Response(200);
				log.info("Sending response: " + response.toString());
				try {
					for (PlayerSession playerSession : playerSessionList) {
						log.debug("Sending response to client " + playerSession.getPlayer().getId());
						objectMapper.writeValue(playerSession.getOutputStream(), response);
					}
				} catch (IOException e) {
					log.error("Failed to send response to client " + playerSession.getPlayer().getId());
				}
			}
		} catch (Exception e) {
			log.error("Failed to process request!", e);
		} finally {
			try {
				if (playerSession.getSocket().isConnected()) {
					playerSession.getSocket().close();
					playerSessionList.remove(playerSession);
				}
			} catch (IOException e) {
				log.error("Unable to close socket!", e);
			}
		}
	}
	
}
