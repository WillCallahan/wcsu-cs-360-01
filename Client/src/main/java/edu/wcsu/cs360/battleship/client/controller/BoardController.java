package edu.wcsu.cs360.battleship.client.controller;

import edu.wcsu.cs360.battleship.client.service.ServerConnectionHandlerService;
import javafx.fxml.Initializable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable {
	
	private Log log = LogFactory.getLog(this.getClass());
	@Inject
	private ServerConnectionHandlerService serverConnectionHandlerService;
	
	public BoardController() {
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		log.info("Initializing " + getClass());
	}
	
	
	
}
