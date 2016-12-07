package edu.wcsu.cs360.battleship.common.domain.trans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Player> playerList;

	private int currentPlayerTurnIndex;

	public Game() {
		playerList = new ArrayList<>();
		currentPlayerTurnIndex = 0;
	}

	public List<Player> getPlayerList() {
		return playerList;
	}

	@JsonIgnore
	public void setCurrentPlayerTurnToNext() {
		currentPlayerTurnIndex++;
	}

	public int getCurrentPlayerTurnIndex() {
		return this.currentPlayerTurnIndex % playerList.size();
	}

	@JsonIgnore
	public Player getPlayerById(long id) {
		for (Player player : playerList) {
			if (player.getId() == id)
				return player;
		}
		throw new IllegalArgumentException("Invalid Player Id; Id not found in playerList!");
	}

	/**
	 * Gets the opponent of the {@link Player} with a matching {@link Player#id}
	 *
	 * @param id Id of the current player
	 * @return Opponents of the {@link Player} with a matching {@link Player#id}
	 */
	@JsonIgnore
	public List<Player> getOpponentList(long id) {
		List<Player> opponentList = new ArrayList<>();
		for (Player player : this.playerList) {
			if (player.getId() != id)
				opponentList.add(player);
		}
		return opponentList;
	}

	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}

	/**
	 * Checks if a player has no more ships
	 *
	 * False:
	 *      Ships remain in the player's board
	 * True:
	 *      There are no ships left in a player's board
	 *
	 * @return Whether or not a player has no more ships
	 */
	public boolean shipsLeft() {
		for (Player player : playerList) {
			for (int y = 0; y < player.getBoard().getBoard().length; y++) {
				for (int x = 0; x < player.getBoard().getBoard()[y].length; x++) {
					if (player.getBoard().getBoard()[y][x] > 1) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
