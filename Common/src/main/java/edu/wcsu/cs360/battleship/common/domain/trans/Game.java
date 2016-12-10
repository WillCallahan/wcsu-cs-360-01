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
	
	@JsonIgnore
	public void setCurrentPlayerTurnToNext() {
		currentPlayerTurnIndex++;
	}
	
	public int getCurrentPlayerTurnIndex() {
		return this.currentPlayerTurnIndex % playerList.size();
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
	
	public List<Player> getPlayerList() {
		return playerList;
	}
	
	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}
}
