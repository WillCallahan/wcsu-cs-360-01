package edu.wcsu.cs360.battleship.client.controller;

import edu.wcsu.cs360.battleship.client.service.ServerConnectionService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
	
	private Log log = LogFactory.getLog(this.getClass());
	@Inject
	private ServerConnectionService serverConnectionService;
	
	public LoginController() {
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		log.info("Initializing " + getClass());
	}
	
	public void onHelpMenuAboutClick(ActionEvent actionEvent) {
		log.info("I just got clicked!");
		log.info("The server connection service... " + serverConnectionService);
		serverConnectionService.run();
	}
}
