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
	
	public List<Player> getPlayerList() {
		return playerList;
	}
	
	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}
}
