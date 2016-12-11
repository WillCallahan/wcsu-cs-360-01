package edu.wcsu.cs360.battleship.common.utility;

import edu.wcsu.cs360.battleship.common.domain.trans.Player;

import java.util.ArrayList;
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
	
	/**
	 * Gets all {@link Player} that do not have a matching {@link Player#id}
	 *
	 * @param playerList List of {@link Player} to search through
	 * @param id         Id of the player to exclude from the search
	 * @return All {@link Player} without a matching {@link Player#id}
	 */
	public static List<Player> getPlayerListByNotId(List<Player> playerList, Long id) {
		List<Player> opponentList = new ArrayList<>();
		for (Player player : playerList) {
			if (player.getId() != id)
				opponentList.add(player);
		}
		return opponentList;
	}
	
	/**
	 * Checks if a player has any more ships left
	 * <p>
	 * False:
	 * Living Ships remain in the player's board
	 * True:
	 * There are no living ships left in a player's board
	 *
	 * @return Whether or not a player has no more ships
	 */
	public static boolean hasShipLeft(Player player) {
		for (int y = 0; y < player.getBoard().getBoard().length; y++) {
			for (int x = 0; x < player.getBoard().getBoard()[y].length; x++) {
				if (player.getBoard().getBoard()[y][x] > 1) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if a list of players has a winner. A winner is determined by a player that has not living ships left
	 *
	 * @param playerList List of {@link Player} to check
	 * @return {@code null} if there is no winner or the {@link Player} that won
	 */
	public static Player getWinner(List<Player> playerList) {
		if (playerList == null)
			throw new IllegalArgumentException("List of players cannot be null!");
		for (int i = 0; i < playerList.size(); i++) {
			if (!hasShipLeft(playerList.get(i)))
				return playerList.get(i);
		}
		return null;
	}
	
}
