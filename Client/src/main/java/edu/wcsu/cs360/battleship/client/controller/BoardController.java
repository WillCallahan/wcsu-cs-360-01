package edu.wcsu.cs360.battleship.client.controller;

import edu.wcsu.cs360.battleship.client.service.general.BattleshipGameBoardDrawService;
import edu.wcsu.cs360.battleship.client.service.io.GameConnectionHandlerService;
import edu.wcsu.cs360.battleship.client.service.io.ServerConnectionHandlerService;
import edu.wcsu.cs360.battleship.common.domain.singleton.ApplicationSession;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;
import edu.wcsu.cs360.battleship.common.domain.trans.Board;
import edu.wcsu.cs360.battleship.common.service.serialize.ObjectMapperClassCastService;
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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable {
	
	private Log log = LogFactory.getLog(this.getClass());
	private BattleshipGameBoardDrawService battleshipGameBoardDrawService;
	@Inject
	private GameConnectionHandlerService gameConnectionHandlerService;
	@Inject
	private ApplicationSession applicationSession;
	@Inject
	private ServerConnectionHandlerService serverConnectionHandlerService;
	
	public BoardController() throws IOException {
		gameConnectionHandlerService = null;
	}
	
	//region Button Event Handlers
	
	public void onStartGameButtonClick(ActionEvent actionEvent) throws IOException {
		gameConnectionHandlerService.initialize();
		gameConnectionHandlerService.listen(this::handleResponseFromServer);
	}
	
	//endregion
	
	//region Drag Event Handlers
	
	public void onPlayerPaneClicked(MouseEvent mouseEvent) {
		battleshipGameBoardDrawService.togglePlayerShip((int) mouseEvent.getX(), (int) mouseEvent.getY());
		if (battleshipGameBoardDrawService.getPlayerBattleshipBoardDrawService().getTotalShipImages() == Board.MAX_SHIPS) {
			startGameButton.setDisable(false);
			notificationLabel.setText("You may now start the game!");
		} else {
			startGameButton.setDisable(true);
			notificationLabel.setText("Please add " + (Board.MAX_SHIPS - battleshipGameBoardDrawService.getPlayerBattleshipBoardDrawService().getTotalShipImages()) + " more ships on the board.");
		}
	}
	
	//endregion
	
	private void handleResponseFromServer(Response response) {
		ObjectMapperClassCastService objectMapperClassCastService = new ObjectMapperClassCastService();
		//TODO Draw board
		objectMapperClassCastService.cast(response.getBody(), Board.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		log.info("Initializing " + getClass());
		battleshipGameBoardDrawService = new BattleshipGameBoardDrawService(playerPane, opponentPane, Board.MAX_SHIPS);
		notificationLabel.setText("Please add " + Board.MAX_SHIPS + " more ships to the board.");
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
