package edu.wcsu.cs360.battleship.server.utility.session;

import edu.wcsu.cs360.battleship.common.domain.trans.Player;
import edu.wcsu.cs360.battleship.server.domain.session.PlayerSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility methods for the {@link edu.wcsu.cs360.battleship.server.domain.session.PlayerSession}
 */
public class PlayerSessionUtility {
	
	/**
	 * Gets a {@link PlayerSession} with a matching {@link PlayerSession#sessionId}
	 *
	 * @param playerSessionList List of {@link PlayerSession} to look through
	 * @param sessionId         {@link PlayerSession#sessionId} to search by
	 * @return Matching {@link PlayerSession}
	 * @throws IllegalArgumentException If duplicate {@link PlayerSession#sessionId} exists or if there is no match
	 */
	public static PlayerSession findOneBySessionId(List<PlayerSession> playerSessionList, long sessionId) {
		PlayerSession foundPlayerSession = null;
		for (PlayerSession playerSession : playerSessionList) {
			if (playerSession.getSessionId() == sessionId) {
				if (foundPlayerSession != null) //There is a duplicate; this is not allowed
					throw new IllegalArgumentException("Cannot have Player Session with duplicate sessionId of " + sessionId);
				foundPlayerSession = playerSession;
			}
		}
		if (foundPlayerSession == null)
			throw new IllegalArgumentException("Player not found with sessionId of " + sessionId);
		return foundPlayerSession;
	}
	
	/**
	 * Gets a {@link PlayerSession} with a matching reference to a {@link PlayerSession}
	 *
	 * @param playerSessionList List of {@link PlayerSession} to look through
	 * @param playerSession     {@link PlayerSession} reference to search by
	 * @return Matching {@link PlayerSession}
	 * @throws IllegalArgumentException If duplicate {@link PlayerSession} exists or if there is no match
	 */
	public static PlayerSession findOneByReference(List<PlayerSession> playerSessionList, PlayerSession playerSession) {
		PlayerSession foundPlayerSession = null;
		for (PlayerSession playerSessionInList : playerSessionList) {
			if (playerSessionInList == playerSession) {
				if (foundPlayerSession != null) //There is a duplicate; this is not allowed
					throw new IllegalArgumentException("Cannot have Player Session with duplicate reference of " + playerSessionList);
				foundPlayerSession = playerSession;
			}
		}
		if (foundPlayerSession == null)
			throw new IllegalArgumentException("Player not found with reference of " + playerSessionList);
		return foundPlayerSession;
	}
	
	/**
	 * Gets a list of {@link PlayerSession#player} from a list of {@link PlayerSession}
	 *
	 * @param playerSessionList List of {@link PlayerSession} to get {@link PlayerSession#player} from
	 * @return All {@link PlayerSession} from a list of {@link PlayerSession#player}
	 */
	public static List<Player> getPlayerList(List<PlayerSession> playerSessionList) {
		List<Player> playerList = new ArrayList<>();
		for (PlayerSession playerSession : playerSessionList)
			playerList.add(playerSession.getPlayer());
		return playerList;
	}
	
}
