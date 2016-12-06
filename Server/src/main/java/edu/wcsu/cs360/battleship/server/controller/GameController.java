package edu.wcsu.cs360.battleship.server.controller;

import edu.wcsu.cs360.battleship.common.annotation.Controller;
import edu.wcsu.cs360.battleship.common.annotation.Inject;
import edu.wcsu.cs360.battleship.common.annotation.Mapping;
import edu.wcsu.cs360.battleship.common.domain.enumeration.RequestMethod;
import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;
import edu.wcsu.cs360.battleship.common.domain.trans.Game;
import edu.wcsu.cs360.battleship.common.domain.trans.Player;
import edu.wcsu.cs360.battleship.common.repository.IUserRepository;
import edu.wcsu.cs360.battleship.server.domain.session.PlayerSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

@Controller
public class GameController {

	private Log log = LogFactory.getLog(this.getClass());

	@Inject
	private IUserRepository iUserRepository;

	@Mapping(requestMethod = RequestMethod.GET)
	public void makeMove(Request<Game> request, Game game) {
		
	}
	
	@Mapping(requestMethod = RequestMethod.POST)
	public Response<Game> updatePlayerBoard(Request<Player> playerRequest, List<PlayerSession> playerSessionsList, PlayerSession playerSession) {
		if (playerSession.getPlayer() == null)
			playerSession.setPlayer(playerRequest.getBody());
		if (playerSession.getPlayer().getId() != playerRequest.getBody().getId())
			throw new SecurityException("Player " + playerSession.getPlayer().getId() + " may not modify the board of " + playerRequest.getBody().getId());
		log.info("updatePlayerBoard");
		log.info("Player " + playerSession.getPlayer());
		log.info("Request " + playerRequest);
		return new Response<>();
	}

}
