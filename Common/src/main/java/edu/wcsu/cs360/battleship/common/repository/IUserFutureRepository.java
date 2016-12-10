package edu.wcsu.cs360.battleship.common.repository;

import edu.wcsu.cs360.battleship.common.domain.entity.User;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;

import java.util.concurrent.Future;

/**
 * Calls a remote server to request resources related to a {@link User}
 *
 * All requests are performed on a separate thread. When the thread has completed execution, a {@link Future} is
 * returned, containing a {@link Response} with a {@link Response#body} of the type {@link User}
 */
public interface IUserFutureRepository extends ICrudFuture<User, Long> {
	
	/**
	 * @return Response from the server in the future
	 * @see edu.wcsu.cs360.battleship.common.repository.IUserRepository#findOneByUsername(String)
	 */
	Future<Response> findOneByUsername(String username);
	
	/**
	 * @return Response from the server in the future
	 * @see edu.wcsu.cs360.battleship.common.repository.IUserRepository#findOneByUsernameAndPassword(String, String)
	 */
	Future<Response> findOneByUsernameAndPassword(String username, String password);
	
}
