package edu.wcsu.cs360.battleship.server.controller;

import edu.wcsu.cs360.battleship.common.annotation.Controller;
import edu.wcsu.cs360.battleship.common.annotation.Inject;
import edu.wcsu.cs360.battleship.common.annotation.Mapping;
import edu.wcsu.cs360.battleship.common.domain.enumeration.RequestMethod;
import edu.wcsu.cs360.battleship.common.repository.IUserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

@Controller
public class GameController {

	private Log log = LogFactory.getLog(this.getClass());

	@Inject
	private IUserRepository iUserRepository;

	public GameController() {

	}

	@Mapping(requestMethod = RequestMethod.GET)
	public void getTest() {
		log.info("User repository: " + iUserRepository.findAll());
	}

}
