package edu.wcsu.cs360.battleship.client.controller;

import com.airhacks.afterburner.injection.Injector;
import edu.wcsu.cs360.battleship.client.service.ServerConnectionService;
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
	
	private void setupInjector() throws Exception {
		log.info("Creating dependency injection instances...");
		Injector.setModelOrService(ServerConnectionService.class, new ServerConnectionService("localhost", 8000));
		log.info("Successfully created dependency injection instances!");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start(Stage stage) {
		log.info("Starting application!");
		try {
			setupInjector();
		} catch (Exception e) {
			log.error("Unable to start application!", e);
		}
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
