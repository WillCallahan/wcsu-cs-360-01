package edu.wcsu.cs360.battleship.server.controller;

import edu.wcsu.cs360.battleship.common.repository.IUserRepository;
import edu.wcsu.cs360.battleship.common.repository.UserRepository;
import edu.wcsu.cs360.battleship.common.service.di.DependencyInjectionService;
import edu.wcsu.cs360.battleship.common.service.aop.DispatcherService;
import edu.wcsu.cs360.battleship.common.service.aop.IDispatcher;
import edu.wcsu.cs360.battleship.common.service.io.IConnectionListenerService;
import edu.wcsu.cs360.battleship.common.service.io.PropertyFileService;
import edu.wcsu.cs360.battleship.server.service.ClientConnectionHandlerService;
import edu.wcsu.cs360.battleship.server.service.StatelessConnectionServer;
import edu.wcsu.cs360.battleship.server.service.IConnectionServer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ServerMainController {
	
	private Log log = LogFactory.getLog(this.getClass());
	private IDispatcher iDispatcher;
	private DependencyInjectionService dependencyInjectionService;
	private IConnectionServer iConnectionServer;
	private IConnectionListenerService iConnectionListenerService;
	
	public ServerMainController() {
		PropertyFileService propertyFileService = new PropertyFileService("database.properties");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(propertyFileService.getProperty("jpa.persistence.unit.name").toString());
		dependencyInjectionService = new DependencyInjectionService();
		dependencyInjectionService.registerDependency(IUserRepository.class, new UserRepository(entityManagerFactory.createEntityManager()));
		iDispatcher = new DispatcherService(dependencyInjectionService, GameController.class, AuthenticationController.class, UserApiController.class);
		iConnectionListenerService = new ClientConnectionHandlerService(iDispatcher);
		iConnectionServer = new StatelessConnectionServer(iConnectionListenerService);
	}
	
	public static void main(String args[]) {
		ServerMainController serverMainController = new ServerMainController();
		serverMainController.run();
	}
	
	public void run() {
		log.info("Starting application!");
		iConnectionServer.run();
		log.info("Ending application!");
	}
	
}
