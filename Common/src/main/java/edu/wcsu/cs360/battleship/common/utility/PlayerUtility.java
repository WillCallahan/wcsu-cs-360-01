package edu.wcsu.cs360.battleship.common.utility;

import edu.wcsu.cs360.battleship.common.domain.trans.Player;

import java.util.List;

/**
 * Utility methods for a {@link edu.wcsu.cs360.battleship.common.domain.trans.Player}
 */
public class PlayerUtility {
	
	private PlayerUtility() {
		
	}
	
	/**
	 * Gets a a {@link Player} by {@link Player#id}
	 *
	 * @param playerList List of {@link Player} to look through
	 * @param id         Id of {@link Player#id} to lookup
	 * @return Matching {@link Player}
	 * @throws IllegalArgumentException If no player is found
	 */
	public static Player getPlayerById(List<Player> playerList, Long id) {
		if (id == null)
			throw new IllegalArgumentException("ID cannot be null!");
		for (Player player : playerList) {
			if (player.getId() == id)
				return player;
		}
		throw new IllegalArgumentException("Invalid Player Id; Id not found in playerList!");
	}
	
}
