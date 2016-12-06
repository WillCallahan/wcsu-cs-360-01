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
	
	public int getCurrentPlayerTurnIndex() {
		return currentPlayerTurnIndex;
	}
	
	public void setCurrentPlayerTurnIndex(int currentPlayerTurnIndex) {
		this.currentPlayerTurnIndex = currentPlayerTurnIndex;
	}
	
	@JsonIgnore
	public Player getPlayerById(long id) {
		for (Player player : playerList) {
			if (player.getId() == id)
				return player;
		}
		throw new IllegalArgumentException("Invalid Player Id; Id not found in playerList!");
	}
	
	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}
}
