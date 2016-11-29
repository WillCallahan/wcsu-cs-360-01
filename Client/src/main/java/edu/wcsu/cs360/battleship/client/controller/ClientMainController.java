package edu.wcsu.cs360.battleship.client.controller;

import com.airhacks.afterburner.injection.Injector;
import com.sun.javafx.css.StyleManager;
import edu.wcsu.cs360.battleship.client.service.ServerConnectionHandlerService;
import edu.wcsu.cs360.battleship.client.view.LoginView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ClientMainController extends Application {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	public static void main(String args[]) {
		launch(args);
	}
	
	private void setupInjector() throws Exception {
		log.info("Creating dependency injection instances...");
		Injector.setModelOrService(ServerConnectionHandlerService.class, new ServerConnectionHandlerService("localhost", 8000));
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
		LoginView loginView = new LoginView();
		Scene scene = new Scene(loginView.getView());
		stage.setScene(scene);
		stage.setOnCloseRequest(event -> Platform.exit());
		stage.show();
		StyleManager.getInstance().addUserAgentStylesheet(getClass().getResource("/css/main.css").toExternalForm());
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
