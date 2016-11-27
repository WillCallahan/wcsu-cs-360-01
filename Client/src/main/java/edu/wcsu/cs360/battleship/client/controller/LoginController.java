package edu.wcsu.cs360.battleship.client.controller;

import edu.wcsu.cs360.battleship.client.service.ServerConnectionHandlerService;
import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
	
	private Log log = LogFactory.getLog(this.getClass());
	@Inject
	private ServerConnectionHandlerService serverConnectionHandlerService;
	
	public LoginController() {
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		log.info("Initializing " + getClass());
	}
	
	public void onHelpMenuAboutClick(ActionEvent actionEvent) throws InterruptedException {
		log.info("I just got clicked!");
		log.info("The server connection service... " + serverConnectionHandlerService);
		Request request = new Request();
		request.setContentType("application/json");
		request.setBody("Sample");
		request.setTarget("gameController.getTest");
		serverConnectionHandlerService.send(request);
	}
	
	public void onFileMenuCloseClick(ActionEvent actionEvent) {
		((Stage) (menuBar).getScene().getWindow()).close();
	}
	
	@FXML
	private MenuBar menuBar;
	@FXML
	private MenuItem fileMenuClose;
}
