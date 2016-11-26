package edu.wcsu.cs360.battleship.server.controller;

import edu.wcsu.cs360.battleship.common.repository.IUserRepository;
import edu.wcsu.cs360.battleship.common.repository.UserRepository;
import edu.wcsu.cs360.battleship.common.service.DependencyInjectionService;
import edu.wcsu.cs360.battleship.common.service.DispatcherService;
import edu.wcsu.cs360.battleship.common.service.IDispatcher;
import edu.wcsu.cs360.battleship.common.service.PropertyFileService;
import edu.wcsu.cs360.battleship.server.service.DependencyInjectionConnectionServer;
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
	
	public ServerMainController() {
		PropertyFileService propertyFileService = new PropertyFileService("database.properties");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(propertyFileService.getProperty("jpa.persistence.unit.name").toString());
		dependencyInjectionService = new DependencyInjectionService();
		dependencyInjectionService.registerDependency(IUserRepository.class, new UserRepository(entityManagerFactory.createEntityManager()));
		iDispatcher = new DispatcherService(dependencyInjectionService, GameController.class);
		iConnectionServer = new DependencyInjectionConnectionServer(iDispatcher);
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
