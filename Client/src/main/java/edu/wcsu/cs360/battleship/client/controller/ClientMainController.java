package edu.wcsu.cs360.battleship.client.controller;

import edu.wcsu.cs360.battleship.client.view.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ClientMainController extends Application {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	public static void main(String args[]) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		log.info("Starting application!");
		LoginView loginView = new LoginView();
	}
	
	@Override
	public void stop() throws Exception {
		log.info("Ending application!");
		super.stop();
	}
}
