package edu.wcsu.cs360.battleship.common.repository;

import edu.wcsu.cs360.battleship.common.domain.entity.User;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;

import java.util.concurrent.Future;

public interface IUserFutureRepository extends ICrudFuture<User> {
	
	/**
	 * @param user User to send to the server
	 * @return Response from the server in the future
	 * @see edu.wcsu.cs360.battleship.common.repository.IUserRepository#findOneByUsername(String)
	 */
	Future<Response> findOneByUsername(User user);
	
	/**
	 * @param user User to send to the server
	 * @return Response from the server in the future
	 * @see edu.wcsu.cs360.battleship.common.repository.IUserRepository#findOneByUsernameAndPassword(String, String)
	 */
	Future<Response> findOneByUsernameAndPassword(User user);
	
}
