package edu.wcsu.cs360.battleship.server.controller;

import edu.wcsu.cs360.battleship.common.annotation.Controller;
import edu.wcsu.cs360.battleship.common.annotation.Inject;
import edu.wcsu.cs360.battleship.common.annotation.Mapping;
import edu.wcsu.cs360.battleship.common.domain.entity.User;
import edu.wcsu.cs360.battleship.common.domain.enumeration.RequestMethod;
import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;
import edu.wcsu.cs360.battleship.common.repository.IUserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Controller
public class AuthenticationController {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Inject
	private IUserRepository iUserRepository;
	
	@Mapping(requestMethod = RequestMethod.GET)
	public Response authenticateByUsernameAndPassword(Request request, User user) {
		return new Response(iUserRepository.findOneByUsernameAndPassword(user.getUsername(), user.getPassword()), 200);
	}
	
}
