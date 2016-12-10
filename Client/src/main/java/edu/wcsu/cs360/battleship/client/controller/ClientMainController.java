package edu.wcsu.cs360.battleship.client.controller;

import com.airhacks.afterburner.injection.Injector;
import com.sun.javafx.css.StyleManager;
import edu.wcsu.cs360.battleship.common.repository.UserFutureRepository;
import edu.wcsu.cs360.battleship.client.service.io.GameConnectionHandlerService;
import edu.wcsu.cs360.battleship.client.service.io.ServerConnectionHandlerService;
import edu.wcsu.cs360.battleship.client.utility.general.ViewUtility;
import edu.wcsu.cs360.battleship.client.utility.notification.AlertUtility;
import edu.wcsu.cs360.battleship.client.view.LoginView;
import edu.wcsu.cs360.battleship.common.repository.IUserFutureRepository;
import edu.wcsu.cs360.battleship.common.service.io.IServerConnectionHandlerService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Main class for the client end of the application
 */
public class ClientMainController extends Application {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	public static void main(String args[]) {
		launch(args);
	}
	
	private void setupInjector() throws Exception {
		log.info("Creating dependency injection instances...");
		IServerConnectionHandlerService iServerConnectionHandlerService = new ServerConnectionHandlerService("localhost", 8000);
		Injector.setModelOrService(IServerConnectionHandlerService.class, iServerConnectionHandlerService);
		Injector.setModelOrService(IUserFutureRepository.class, new UserFutureRepository(iServerConnectionHandlerService));
		Injector.setModelOrService(GameConnectionHandlerService.class, new GameConnectionHandlerService("localhost", 8010));
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
			return;
		}
		Thread.setDefaultUncaughtExceptionHandler((t, e) -> AlertUtility.alert(e, "Please contact an administrator."));
		ViewUtility.replace(new LoginView(), stage);
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
