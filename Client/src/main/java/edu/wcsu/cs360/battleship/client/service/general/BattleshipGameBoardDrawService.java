package edu.wcsu.cs360.battleship.client.service.general;

import edu.wcsu.cs360.battleship.client.controller.BoardController;
import edu.wcsu.cs360.battleship.client.service.canvas.BattleshipBoardDrawService;
import javafx.scene.layout.Pane;

import java.net.URL;

public class BattleshipGameBoardDrawService {
	
	private static final URL EMPTY_ICON_LOCATION = BoardController.class.getResource("/images/water.gif");
	private static final URL SHIP_ICON_LOCATION = BoardController.class.getResource("/images/destroyer-icon.png");
	private static final URL HIT_ICON_LOCATION = BoardController.class.getResource("/images/fire-icon.png");
	private static final URL MISS_ICON_LOCATION = BoardController.class.getResource("/images/error-icon.png");
	
	private BattleshipBoardDrawService opponentBattleshipBoardDrawService;
	private BattleshipBoardDrawService playerBattleshipBoardDrawService;
	private int maxShips;
	
	private BattleshipGameBoardDrawService() {
		
	}
	
	public BattleshipGameBoardDrawService(Pane playerPane, Pane opponentPane, int maxShips) {
		opponentBattleshipBoardDrawService = new BattleshipBoardDrawService(opponentPane, EMPTY_ICON_LOCATION, SHIP_ICON_LOCATION, HIT_ICON_LOCATION, MISS_ICON_LOCATION);
		playerBattleshipBoardDrawService = new BattleshipBoardDrawService(playerPane, EMPTY_ICON_LOCATION, SHIP_ICON_LOCATION, HIT_ICON_LOCATION, MISS_ICON_LOCATION);
		opponentPane.heightProperty().addListener((observable, oldValue, newValue) -> updateBoard());
		playerPane.heightProperty().addListener((observable, oldValue, newValue) -> updateBoard());
		opponentPane.widthProperty().addListener((observable, oldValue, newValue) -> updateBoard());
		playerPane.widthProperty().addListener((observable, oldValue, newValue) -> updateBoard());
		this.maxShips = maxShips;
	}
	
	public void updateBoard() {
		opponentBattleshipBoardDrawService.draw();
		playerBattleshipBoardDrawService.draw();
	}
	
	public void togglePlayerShip(int x, int y) {
		if (playerBattleshipBoardDrawService.isCursorLocationAShipImage(x, y)) {
			//If the location is already a ship, clear it
			playerBattleshipBoardDrawService.setCursorLocationToEmpty(x, y);
			playerBattleshipBoardDrawService.draw();
		} else if (playerBattleshipBoardDrawService.getTotalShipImages() < maxShips) {
			//Else, make the location a ship if the player has not added the max ships
			playerBattleshipBoardDrawService.setCursorLocationToShip(x, y);
			playerBattleshipBoardDrawService.draw();
		}
	}
	
	public BattleshipBoardDrawService getOpponentBattleshipBoardDrawService() {
		return opponentBattleshipBoardDrawService;
	}
	
	public void setOpponentBattleshipBoardDrawService(BattleshipBoardDrawService opponentBattleshipBoardDrawService) {
		this.opponentBattleshipBoardDrawService = opponentBattleshipBoardDrawService;
	}
	
	public BattleshipBoardDrawService getPlayerBattleshipBoardDrawService() {
		return playerBattleshipBoardDrawService;
	}
	
	public void setPlayerBattleshipBoardDrawService(BattleshipBoardDrawService playerBattleshipBoardDrawService) {
		this.playerBattleshipBoardDrawService = playerBattleshipBoardDrawService;
	}
}
