package edu.wcsu.cs360.battleship.server.controller;

import edu.wcsu.cs360.battleship.common.repository.IUserRepository;
import edu.wcsu.cs360.battleship.common.repository.UserRepository;
import edu.wcsu.cs360.battleship.common.service.aop.DispatcherService;
import edu.wcsu.cs360.battleship.common.service.aop.IDispatcher;
import edu.wcsu.cs360.battleship.common.service.di.DependencyInjectionService;
import edu.wcsu.cs360.battleship.common.service.io.PropertyFileService;
import edu.wcsu.cs360.battleship.server.service.ClientConnectionHandlerService;
import edu.wcsu.cs360.battleship.server.service.GameConnectionServer;
import edu.wcsu.cs360.battleship.server.service.StatelessConnectionServer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Main class for the serve end of the application
 */
public class ServerMainController {
	
	private Log log = LogFactory.getLog(this.getClass());
	private StatelessConnectionServer statelessConnectionServer;
	private GameConnectionServer gameConnectionServer;
	
	public ServerMainController() {
		PropertyFileService databasePropertyFileService = new PropertyFileService("database.properties");
		PropertyFileService serverPropertyFileService = new PropertyFileService("server.properties");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(databasePropertyFileService.getProperty("jpa.persistence.unit.name").toString());
		DependencyInjectionService dependencyInjectionService = new DependencyInjectionService();
		dependencyInjectionService.registerDependency(IUserRepository.class, new UserRepository(entityManagerFactory.createEntityManager()));
		IDispatcher iDispatcher = new DispatcherService(dependencyInjectionService, GameController.class, AuthenticationController.class, UserApiController.class);
		statelessConnectionServer = new StatelessConnectionServer(new ClientConnectionHandlerService(iDispatcher), Integer.parseInt(serverPropertyFileService.getProperty("serverPort").toString()));
		gameConnectionServer = new GameConnectionServer(iDispatcher, Integer.parseInt(serverPropertyFileService.getProperty("gameServerPort").toString()));
	}
	
	public static void main(String args[]) {
		ServerMainController serverMainController = new ServerMainController();
		serverMainController.run();
	}
	
	public void run() {
		log.info("Starting application!");
		statelessConnectionServer.start();
		gameConnectionServer.start();
		log.info("Ending application!");
	}
	
}
