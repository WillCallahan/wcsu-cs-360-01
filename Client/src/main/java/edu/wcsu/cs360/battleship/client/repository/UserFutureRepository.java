package edu.wcsu.cs360.battleship.client.repository;

import edu.wcsu.cs360.battleship.client.service.ServerConnectionHandlerService;
import edu.wcsu.cs360.battleship.common.domain.entity.User;
import edu.wcsu.cs360.battleship.common.domain.enumeration.RequestMethod;
import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;
import edu.wcsu.cs360.battleship.common.repository.IUserFutureRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;
import java.util.concurrent.Future;

public class UserFutureRepository implements IUserFutureRepository {
	
	private Log log = LogFactory.getLog(this.getClass());
	@Inject
	private ServerConnectionHandlerService serverConnectionHandlerService;

	public UserFutureRepository() {

	}

	public UserFutureRepository(ServerConnectionHandlerService serverConnectionHandlerService) {
		this.serverConnectionHandlerService = serverConnectionHandlerService;
	}
	
	
	@Override
	public Future<Response> findOneByUsername(User request) {
		return serverConnectionHandlerService.send(new Request<>("userApiController.findOneByUsername", RequestMethod.GET, request));
	}
	
	@Override
	public Future<Response> findOneByUsernameAndPassword(User request) {
		return serverConnectionHandlerService.send(new Request<>("userApiController.findOneByUsernameAndPassword", RequestMethod.GET, request));
	}
	
	@Override
	public Future<Response> findOne(User request) {
		return serverConnectionHandlerService.send(new Request<>("userApiController.findOne", RequestMethod.GET, request));
	}
	
	@Override
	public Future<Response> findAll(User request) {
		return serverConnectionHandlerService.send(new Request<>("userApiController.findAll", RequestMethod.GET, request));
	}
	
	@Override
	public Future<Response> count(User request) {
		return serverConnectionHandlerService.send(new Request<>("userApiController.count", RequestMethod.GET, request));
	}
	
	@Override
	public Future<Response> save(User request) {
		return serverConnectionHandlerService.send(new Request<>("userApiController.save", RequestMethod.UPDATE, request));
	}
	
	@Override
	public Future<Response> saveAll(Iterable<User> request) {
		return serverConnectionHandlerService.send(new Request<>("userApiController.saveAll", RequestMethod.UPDATE, request));
	}
	
	@Override
	public Future<Response> deleteById(User request) {
		return serverConnectionHandlerService.send(new Request<>("userApiController.deleteById", RequestMethod.DELETE, request));
	}
	
	@Override
	public Future<Response> delete(User request) {
		return serverConnectionHandlerService.send(new Request<>("userApiController.delete", RequestMethod.DELETE, request));
	}
}
