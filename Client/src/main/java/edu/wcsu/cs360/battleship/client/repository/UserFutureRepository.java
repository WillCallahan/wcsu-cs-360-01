package edu.wcsu.cs360.battleship.client.repository;

import edu.wcsu.cs360.battleship.client.service.io.ServerConnectionHandlerService;
import edu.wcsu.cs360.battleship.common.domain.entity.User;
import edu.wcsu.cs360.battleship.common.domain.enumeration.RequestMethod;
import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;
import edu.wcsu.cs360.battleship.common.repository.IUserFutureRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;
import java.util.concurrent.Future;

/**
 * {@inheritDoc}
 */
public class UserFutureRepository implements IUserFutureRepository {
	
	private Log log = LogFactory.getLog(this.getClass());
	@Inject
	private ServerConnectionHandlerService serverConnectionHandlerService;

	public UserFutureRepository() {

	}

	public UserFutureRepository(ServerConnectionHandlerService serverConnectionHandlerService) {
		this.serverConnectionHandlerService = serverConnectionHandlerService;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Future<Response> findOneByUsernameAndPassword(String username, String password) {
		User user = new User(username, password);
		return serverConnectionHandlerService.send(new Request<>("userApiController.findOneByUsernameAndPassword", RequestMethod.GET, user));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Future<Response> findOneByUsername(String username) {
		User user = new User();
		user.setUsername(username);
		return serverConnectionHandlerService.send(new Request<>("userApiController.findOneByUsername", RequestMethod.GET, user));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Future<Response> findOne(Long id) {
		User user = new User();
		user.setUserId(id);
		return serverConnectionHandlerService.send(new Request<>("userApiController.findOne", RequestMethod.GET, user));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Future<Response> findAll() {
		return serverConnectionHandlerService.send(new Request<>("userApiController.findAll", RequestMethod.GET, null));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Future<Response> count() {
		return serverConnectionHandlerService.send(new Request<>("userApiController.count", RequestMethod.GET, null));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Future<Response> save(User user) {
		return serverConnectionHandlerService.send(new Request<>("userApiController.save", RequestMethod.UPDATE, user));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Future<Response> saveAll(Iterable<User> userIterable) {
		return serverConnectionHandlerService.send(new Request<>("userApiController.saveAll", RequestMethod.UPDATE, userIterable));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Future<Response> deleteById(Long id) {
		User user = new User();
		user.setUserId(id);
		return serverConnectionHandlerService.send(new Request<>("userApiController.deleteById", RequestMethod.DELETE, user));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Future<Response> delete(User request) {
		return serverConnectionHandlerService.send(new Request<>("userApiController.delete", RequestMethod.DELETE, request));
	}
}
