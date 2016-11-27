package edu.wcsu.cs360.battleship.client.controller;

import com.airhacks.afterburner.injection.Injector;
import edu.wcsu.cs360.battleship.client.view.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ClientMainController extends Application {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	public static void main(String args[]) {
		launch(args);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start(Stage stage) {
		log.info("Starting application!");
		LoginView loginController = new LoginView();
		Scene scene = new Scene(loginController.getView());
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stop() throws Exception {
		log.info("Stopping application!");
		Injector.forgetAll();
		super.stop();
	}
}
