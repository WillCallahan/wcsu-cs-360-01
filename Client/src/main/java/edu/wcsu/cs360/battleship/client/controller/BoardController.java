package edu.wcsu.cs360.battleship.client.controller;

import edu.wcsu.cs360.battleship.client.service.ServerConnectionHandlerService;
import edu.wcsu.cs360.battleship.client.service.canvas.BoardDrawService;
import edu.wcsu.cs360.battleship.common.domain.singleton.ApplicationSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable {
	
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
	
	//region Event Handlers
	
	public void onStartGameButtonClick(ActionEvent actionEvent) {
		updateBoard();
	}
	
	//endregion
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		log.info("Initializing " + getClass());
		opponentBoardDrawServer = new BoardDrawService(opponentPane);
		playerBoardDrawServer = new BoardDrawService(playerPane);
		updateBoard();
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
