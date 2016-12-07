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
import edu.wcsu.cs360.battleship.server.utility.session.PlayerSessionUtility;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

@Controller
public class GameController {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Inject
	private IUserRepository iUserRepository;
	
	@Mapping(requestMethod = RequestMethod.GET)
	public Response<Game> makeMove(Request<Game> request, Game game) {
		game.setPlayerList(request.getBody().getPlayerList());
		game.setCurrentPlayerTurnToNext();
		return new Response<>(game);
	}
	
	@Mapping(requestMethod = RequestMethod.POST)
	public Response<Game> updatePlayerBoard(Request<Player> playerRequest, List<PlayerSession> playerSessionList, PlayerSession playerSession, Game game) {
		if (playerSession.getPlayer() == null)
			playerSession.setPlayer(playerRequest.getBody());
		if (playerSession.getPlayer().getId() != playerRequest.getBody().getId())
			throw new SecurityException("Player " + playerSession.getPlayer().getId() + " may not modify the board of " + playerRequest.getBody().getId());
		game.setPlayerList(PlayerSessionUtility.getPlayerList(playerSessionList));
		return new Response<>(game);
	}
	
}
