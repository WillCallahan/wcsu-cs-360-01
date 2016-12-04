package edu.wcsu.cs360.battleship.client.controller;

import edu.wcsu.cs360.battleship.client.service.ServerConnectionHandlerService;
import edu.wcsu.cs360.battleship.client.service.canvas.BoardDrawService;
import edu.wcsu.cs360.battleship.common.domain.singleton.ApplicationSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable {
	
	private static final int MAX_SHIPS = 10; //FIXME: put this in the common module
	private static final URL EMPTY_ICON_LOCATION = BoardController.class.getResource("/images/water.gif");
	private static final URL SHIP_ICON_LOCATION = BoardController.class.getResource("/images/destroyer-icon.png");
	private static final URL HIT_ICON_LOCATION = BoardController.class.getResource("/images/fire-icon.png");
	private static final URL MISS_ICON_LOCATION = BoardController.class.getResource("/images/error-icon.png");
	
	private Log log = LogFactory.getLog(this.getClass());
	private BoardDrawService opponentBoardDrawServer;
	private BoardDrawService playerBoardDrawServer;
	@Inject
	private ApplicationSession applicationSession;
	@Inject
	private ServerConnectionHandlerService serverConnectionHandlerService;
	
	public BoardController() {
		opponentBoardDrawServer = null;
		playerBoardDrawServer = null;
	}
	
	//region Button Event Handlers
	
	public void onStartGameButtonClick(ActionEvent actionEvent) {
	}
	
	//endregion
	
	//region Drag Event Handlers
	
	public void onPlayerPaneClicked(MouseEvent mouseEvent) {
		if (playerBoardDrawServer.isCursorLocationAShipImage((int) mouseEvent.getX(), (int) mouseEvent.getY())) {
			playerBoardDrawServer.setCursorLocationToEmpty((int) mouseEvent.getX(), (int) mouseEvent.getY());
			playerBoardDrawServer.draw();
		}
		else {
			if (playerBoardDrawServer.getTotalShipImages() < MAX_SHIPS) {
				playerBoardDrawServer.setCursorLocationToShip((int) mouseEvent.getX(), (int) mouseEvent.getY());
				playerBoardDrawServer.draw();
			}
		}
		if (playerBoardDrawServer.getTotalShipImages() == MAX_SHIPS) {
			startGameButton.setDisable(false);
			notificationLabel.setText("You may now start the game!");
		} else {
			startGameButton.setDisable(true);
			notificationLabel.setText("Please add " + (MAX_SHIPS - playerBoardDrawServer.getTotalShipImages()) + " more ships on the board.");
		}
	}
	
	//endregion
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		log.info("Initializing " + getClass());
		opponentBoardDrawServer = new BoardDrawService(opponentPane, EMPTY_ICON_LOCATION, SHIP_ICON_LOCATION, HIT_ICON_LOCATION, MISS_ICON_LOCATION);
		playerBoardDrawServer = new BoardDrawService(playerPane, EMPTY_ICON_LOCATION, SHIP_ICON_LOCATION, HIT_ICON_LOCATION, MISS_ICON_LOCATION);
		opponentPane.heightProperty().addListener((observable, oldValue, newValue) -> updateBoard());
		playerPane.heightProperty().addListener((observable, oldValue, newValue) -> updateBoard());
		opponentPane.widthProperty().addListener((observable, oldValue, newValue) -> updateBoard());
		playerPane.widthProperty().addListener((observable, oldValue, newValue) -> updateBoard());
		notificationLabel.setText("Please add " + MAX_SHIPS + " more ships on the board.");
	}
	
	private void updateBoard() {
		opponentBoardDrawServer.draw();
		playerBoardDrawServer.draw();
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
