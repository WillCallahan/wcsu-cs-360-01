package edu.wcsu.cs360.battleship.server.controller;

import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.repository.IUserRepository;
import edu.wcsu.cs360.battleship.common.repository.UserRepository;
import edu.wcsu.cs360.battleship.common.service.DependencyInjectionService;
import edu.wcsu.cs360.battleship.common.service.DispatcherService;
import edu.wcsu.cs360.battleship.common.service.IDispatcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

public class ServerMainController {

	private IDispatcher iDispatcher;
	private DependencyInjectionService dependencyInjectionService;
	private Log log = LogFactory.getLog(this.getClass());

	public ServerMainController() {
		dependencyInjectionService = new DependencyInjectionService();
		dependencyInjectionService.registerDependency(IUserRepository.class, new UserRepository());
		iDispatcher = new DispatcherService(dependencyInjectionService, GameController.class);
	}

	public static void main(String args[]) {
		ServerMainController serverMainController = new ServerMainController();
		serverMainController.run();
	}

	public void run() {
		log.info("Starting application!");
		Request request = new Request();
		request.setContentType("application/json");
		request.setBody("Sample");
		request.setTarget("gameController.getTest");
		iDispatcher.dispatch(request);
		log.info("Ending application!");
	}

}
