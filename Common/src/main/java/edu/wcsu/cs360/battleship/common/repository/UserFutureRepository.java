package edu.wcsu.cs360.battleship.common.repository;

import edu.wcsu.cs360.battleship.common.domain.entity.User;
import edu.wcsu.cs360.battleship.common.domain.enumeration.RequestMethod;
import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;
import edu.wcsu.cs360.battleship.common.service.io.IServerConnectionHandlerService;
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
	private IServerConnectionHandlerService iServerConnectionHandlerService;
	
	public UserFutureRepository() {
		
	}
	
	public UserFutureRepository(IServerConnectionHandlerService iServerConnectionHandlerService) {
		this.iServerConnectionHandlerService = iServerConnectionHandlerService;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Future<Response> findOneByUsernameAndPassword(String username, String password) {
		User user = new User(username, password);
		return iServerConnectionHandlerService.send(new Request<>("userApiController.findOneByUsernameAndPassword", RequestMethod.GET, user));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Future<Response> findOneByUsername(String username) {
		User user = new User();
		user.setUsername(username);
		return iServerConnectionHandlerService.send(new Request<>("userApiController.findOneByUsername", RequestMethod.GET, user));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Future<Response> findOne(Long id) {
		User user = new User();
		user.setUserId(id);
		return iServerConnectionHandlerService.send(new Request<>("userApiController.findOne", RequestMethod.GET, user));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Future<Response> findAll() {
		return iServerConnectionHandlerService.send(new Request<>("userApiController.findAll", RequestMethod.GET, null));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Future<Response> count() {
		return iServerConnectionHandlerService.send(new Request<>("userApiController.count", RequestMethod.GET, null));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Future<Response> save(User user) {
		return iServerConnectionHandlerService.send(new Request<>("userApiController.save", RequestMethod.UPDATE, user));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Future<Response> saveAll(Iterable<User> userIterable) {
		return iServerConnectionHandlerService.send(new Request<>("userApiController.saveAll", RequestMethod.UPDATE, userIterable));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Future<Response> deleteById(Long id) {
		User user = new User();
		user.setUserId(id);
		return iServerConnectionHandlerService.send(new Request<>("userApiController.deleteById", RequestMethod.DELETE, user));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Future<Response> delete(User request) {
		return iServerConnectionHandlerService.send(new Request<>("userApiController.delete", RequestMethod.DELETE, request));
	}
}
