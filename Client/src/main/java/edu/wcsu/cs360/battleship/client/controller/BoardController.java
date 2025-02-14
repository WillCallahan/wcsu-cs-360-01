package edu.wcsu.cs360.battleship.client.controller;

import edu.wcsu.cs360.battleship.client.service.canvas.BattleshipBoardDrawService;
import edu.wcsu.cs360.battleship.client.service.general.BattleshipGameBoardDrawService;
import edu.wcsu.cs360.battleship.client.service.io.GameConnectionHandlerService;
import edu.wcsu.cs360.battleship.client.utility.general.ViewUtility;
import edu.wcsu.cs360.battleship.client.utility.notification.AlertUtility;
import edu.wcsu.cs360.battleship.client.view.AboutView;
import edu.wcsu.cs360.battleship.client.view.UserInformationView;
import edu.wcsu.cs360.battleship.common.domain.enumeration.RequestMethod;
import edu.wcsu.cs360.battleship.common.domain.session.ApplicationSession;
import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;
import edu.wcsu.cs360.battleship.common.domain.trans.*;
import edu.wcsu.cs360.battleship.common.service.serialize.ObjectMapperClassCastService;
import edu.wcsu.cs360.battleship.common.utility.PlayerUtility;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller for the {@link edu.wcsu.cs360.battleship.client.view.BoardView}
 */
public class BoardController implements Initializable {
	
	private Log log = LogFactory.getLog(this.getClass());
	private BattleshipGameBoardDrawService battleshipGameBoardDrawService;
	private boolean gameStarted = false;
	private Game game;
	@Inject
	private GameConnectionHandlerService gameConnectionHandlerService;
	@Inject
	private ApplicationSession applicationSession;
	
	public BoardController() throws IOException {
		gameConnectionHandlerService = null;
		game = new Game();
	}
	
	//region Button Event Handlers
	
	public void onStartGameButtonClick(ActionEvent actionEvent) throws IOException {
		gameConnectionHandlerService.initialize();
		gameConnectionHandlerService.listen(this::handleResponseFromServer);
		
		Player player = PlayerUtility.getPlayerById(game.getPlayerList(), applicationSession.getUser().getUserId());
		player.setBoard(new Board(new Tuple(battleshipGameBoardDrawService.getPlayerBattleshipBoardDrawService().getNumberOfHorizontalBoxes(), battleshipGameBoardDrawService.getPlayerBattleshipBoardDrawService().getNumberOfVerticalBoxes())));
		setBoard(battleshipGameBoardDrawService.getPlayerBattleshipBoardDrawService(), player.getBoard());
		
		Request<Player> gameRequest = new Request<>();
		gameRequest.setMethod(RequestMethod.POST);
		gameRequest.setBody(player);
		gameRequest.setTarget("gameController.updatePlayerBoard");
		gameConnectionHandlerService.send(gameRequest);
		startGameButton.setDisable(true);
		gameStarted = true;
	}
	
	/**
	 * Quit the application
	 *
	 * @param actionEvent Action Event
	 */
	public void onQuitButtonClick(ActionEvent actionEvent) {
		Stage stage = (Stage) quitButton.getScene().getWindow();
		stage.close();
	}
	
	/**
	 * Shows the {@link UserInformationView}
	 *
	 * @param actionEvent Action Event
	 */
	public void onUserInfoMenuItemClick(ActionEvent actionEvent) {
		ViewUtility.onTop(new UserInformationView());
	}
	
	/**
	 * Shows the {@link AboutView}
	 *
	 * @param actionEvent Action Event
	 */
	public void onAboutMenuItemClick(ActionEvent actionEvent) {
		ViewUtility.onTop(new AboutView());
	}
	
	//endregion
	
	//region Drag Event Handlers
	
	/**
	 * If the game has not yet started, then this allows a ship to be placed on the player's board in the
	 * location that the player clicked.
	 *
	 * @param mouseEvent Action Event
	 */
	public void onPlayerPaneClicked(MouseEvent mouseEvent) {
		if (!gameStarted) {
			battleshipGameBoardDrawService.togglePlayerShip((int) mouseEvent.getX(), (int) mouseEvent.getY());
			if (battleshipGameBoardDrawService.getPlayerBattleshipBoardDrawService().getTotalShipImages() == Board.MAX_SHIPS) {
				startGameButton.setDisable(false);
				notificationLabel.setText("You may now start the game!");
			} else {
				startGameButton.setDisable(true);
				notificationLabel.setText("Please add " + (Board.MAX_SHIPS - battleshipGameBoardDrawService.getPlayerBattleshipBoardDrawService().getTotalShipImages()) + " more ships on the board.");
			}
		}
	}
	
	/**
	 * If the game has started and it is the player's turn, then a move will be made on the player's board
	 *
	 * @param mouseEvent Action Event
	 * @throws IOException If a socket connection error occurs
	 */
	public void onOpponentPaneClicked(MouseEvent mouseEvent) throws IOException {
		if (gameStarted) {
			Player opponent = PlayerUtility.getPlayerListByNotId(game.getPlayerList(), applicationSession.getUser().getUserId()).get(0);
			if (opponent.getBoard().isMissOrHitLocation(
					battleshipGameBoardDrawService.getOpponentBattleshipBoardDrawService().getHorizontalGridLocationFromX((int) mouseEvent.getX()),
					battleshipGameBoardDrawService.getOpponentBattleshipBoardDrawService().getVerticalGridLocationFromY((int) mouseEvent.getY())
			)) {
				//Player has already made a move at this location
				AlertUtility.alert("Please select a location that you have not made a move in!", Alert.AlertType.WARNING);
			} else {
				//FIXME This does not handle more than two players
				
				opponent.getBoard().hitLocation(
						battleshipGameBoardDrawService.getOpponentBattleshipBoardDrawService().getHorizontalGridLocationFromX((int) mouseEvent.getX()),
						battleshipGameBoardDrawService.getOpponentBattleshipBoardDrawService().getVerticalGridLocationFromY((int) mouseEvent.getY())
				);
				Request<Game> gameRequest = new Request<>();
				gameRequest.setMethod(RequestMethod.POST);
				gameRequest.setBody(game);
				gameRequest.setTarget("gameController.makeMove");
				gameConnectionHandlerService.send(gameRequest);
			}
		}
	}
	
	//endregion
	
	/**
	 * Called every time that the server sends out responses to the client. The {@link Response#body} contains a
	 * {@link Game} object.
	 *
	 * @param response Response from the server
	 */
	private void handleResponseFromServer(Response response) {
		Platform.runLater(() -> {
			ObjectMapperClassCastService objectMapperClassCastService = new ObjectMapperClassCastService();
			this.game = objectMapperClassCastService.cast(response.getBody(), Game.class);
			updateGameBoard(game);
			Player winner = PlayerUtility.getWinner(game.getPlayerList());
			if (winner != null) {
				notificationLabel.setText("Game over!");
				if (winner.getId() == applicationSession.getUser().getUserId())
					AlertUtility.alert("Game over; you lost!", Alert.AlertType.INFORMATION);
				else
					AlertUtility.alert("Game over; you won!", Alert.AlertType.INFORMATION);
				return;
			}
			if (game.getPlayerList().size() == 1) {
				notificationLabel.setText("Waiting for other players...");
				opponentPane.setDisable(true);
			} else if (game.getPlayerList().get(game.getCurrentPlayerTurnIndex()).getId() == applicationSession.getUser().getUserId()) { //Its my turn
				notificationLabel.setText("It's you turn!");
				opponentPane.setDisable(false);
			} else {
				notificationLabel.setText("Its the other player's turn.");
				opponentPane.setDisable(true);
			}
		});
	}
	
	private void updateGameBoard(Game game) {
		this.game.setPlayerList(game.getPlayerList());
		Player player = PlayerUtility.getPlayerById(game.getPlayerList(), applicationSession.getUser().getUserId());
		Player opponent = null;
		List<Player> opponentList = PlayerUtility.getPlayerListByNotId(game.getPlayerList(), applicationSession.getUser().getUserId());
		if (opponentList.size() > 0)
			opponent = opponentList.get(0);
		if (player != null)
			updateBoard(battleshipGameBoardDrawService.getPlayerBattleshipBoardDrawService(), player, false);
		if (opponent != null)
			updateBoard(battleshipGameBoardDrawService.getOpponentBattleshipBoardDrawService(), opponent, true);
	}
	
	private void updateBoard(BattleshipBoardDrawService battleshipBoardDrawService, Player player, boolean isOpponent) {
		for (int i = 0; i < player.getBoard().getBoard().length; i++) {
			for (int o = 0; o < player.getBoard().getBoard()[i].length; o++) {
				if (player.getBoard().getBoard()[i][o] < 0) {
					battleshipBoardDrawService.setLocationToEmpty(i, o);
				} else if (player.getBoard().getBoard()[i][o] == 0) {
					battleshipBoardDrawService.setLocationToEmpty(i, o);
				} else if (player.getBoard().getBoard()[i][o] == 1) {
					if (player.getBoard().getShipList().getByStartTuple(new Tuple(i, o)) == null)
						battleshipBoardDrawService.setLocationToMiss(i, o);
					else
						battleshipBoardDrawService.setLocationToHit(i, o);
				} else if (!isOpponent && player.getBoard().getBoard()[i][o] > 1) {
					battleshipBoardDrawService.setLocationToShip(i, o);
				}
			}
		}
		battleshipBoardDrawService.draw();
	}
	
	private void setBoard(BattleshipBoardDrawService battleshipBoardDrawService, Board board) {
		for (int i = 0; i < battleshipBoardDrawService.getNumberOfHorizontalBoxes(); i++) {
			for (int o = 0; o < battleshipBoardDrawService.getNumberOfVerticalBoxes(); o++) {
				if (battleshipBoardDrawService.isLocationAShipImage(i, o)) {
					board.getShipList().addShip(new Tuple(i, o), new Tuple(i, o), (byte) (i + o));
					board.placeShip(new Ship(new Tuple(i, o), new Tuple(i, o), (byte) (i + o)));
				}
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		log.info("Initializing " + getClass());
		battleshipGameBoardDrawService = new BattleshipGameBoardDrawService(playerPane, opponentPane, Board.MAX_SHIPS);
		notificationLabel.setText("Please add " + Board.MAX_SHIPS + " more ships to the board.");
		Player player = new Player(applicationSession.getUser().getUserId());
		game.getPlayerList().add(player);
	}
	
	@FXML
	private Pane opponentPane;
	@FXML
	private Pane playerPane;
	@FXML
	private Label notificationLabel;
	@FXML
	private Button startGameButton;
	@FXML
	private Button quitButton;
	
}
