package edu.wcsu.cs360.battleship.common.repository;

import edu.wcsu.cs360.battleship.common.domain.entity.User;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;

import java.util.concurrent.Future;

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
