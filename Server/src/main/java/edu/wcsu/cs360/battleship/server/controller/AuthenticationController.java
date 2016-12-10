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

/**
 * Authentication Controller class used to authenticate clients
 */
@Controller
public class AuthenticationController {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Inject
	private IUserRepository iUserRepository;
	
	/**
	 * Authenticated a user by the {@link User#username} and {@link User#password}
	 *
	 * @param request Request from the client
	 * @param user    User from the {@link Request#body}
	 * @return Response
	 */
	@Mapping(requestMethod = RequestMethod.GET)
	public Response authenticateByUsernameAndPassword(Request request, User user) {
		log.info("Attempting to authenticate " + user.getUsername());
		return new Response<>(iUserRepository.findOneByUsernameAndPassword(user.getUsername(), user.getPassword()), 200);
	}
	
}
