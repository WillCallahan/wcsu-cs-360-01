package edu.wcsu.cs360.battleship.client.repository;

import edu.wcsu.cs360.battleship.client.service.ServerConnectionHandlerService;
import edu.wcsu.cs360.battleship.common.domain.entity.User;
import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;
import java.util.concurrent.Future;

public class ClientUserRepository {
	
	private Log log = LogFactory.getLog(this.getClass());
	@Inject
	private ServerConnectionHandlerService serverConnectionHandlerService;
	
	public ClientUserRepository() {
		
	}
	
	public Future<Response> authenticate(User user) {
		return serverConnectionHandlerService.send(new Request<>("authenticationController.authenticateByUsernameAndPassword", user));
	}
	
}
