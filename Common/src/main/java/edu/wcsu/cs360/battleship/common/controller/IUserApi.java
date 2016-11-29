package edu.wcsu.cs360.battleship.common.controller;

import edu.wcsu.cs360.battleship.common.domain.entity.User;
import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;

public interface IUserApi extends ICrudApi<User> {
	
	/**
	 * @param request Request from a client
	 * @return Response from API
	 * @see edu.wcsu.cs360.battleship.common.repository.IUserRepository#findOneByUsername(String)
	 */
	Response<User> findOneByUsername(Request<User> request);
	
	/**
	 * @param request Request from a client
	 * @return Response from API
	 * @see edu.wcsu.cs360.battleship.common.repository.IUserRepository#findOneByUsernameAndPassword(String, String)
	 */
	Response<User> findOneByUsernameAndPassword(Request<User> request);
	
}
