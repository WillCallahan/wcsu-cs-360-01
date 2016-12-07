package edu.wcsu.cs360.battleship.client.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutController implements Initializable {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	public AboutController() {
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		log.info("Initializing " + getClass());
		descriptionLabel.setText("CS 360 Project\n" +
				"Created by\n" +
				"Justin Provenzano\n" +
				"Aboduou Ouro-Salim\n" +
				"William Callahan\n"
		);
	}
	
	@FXML
	private Label descriptionLabel;
	
}
