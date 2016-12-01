package edu.wcsu.cs360.battleship.server.controller;

import edu.wcsu.cs360.battleship.common.annotation.Controller;
import edu.wcsu.cs360.battleship.common.annotation.Inject;
import edu.wcsu.cs360.battleship.common.annotation.Mapping;
import edu.wcsu.cs360.battleship.common.controller.IUserApi;
import edu.wcsu.cs360.battleship.common.domain.entity.User;
import edu.wcsu.cs360.battleship.common.domain.enumeration.RequestMethod;
import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;
import edu.wcsu.cs360.battleship.common.repository.IUserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Controller
public class UserApiController implements IUserApi {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Inject
	private IUserRepository iUserRepository;
	
	public UserApiController() {
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Mapping(requestMethod = RequestMethod.GET)
	@Override
	public Response<User> findOneByUsername(Request<User> request) {
		return new Response<>(iUserRepository.findOneByUsername(request.getBody().getUsername()));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Mapping(requestMethod = RequestMethod.GET)
	@Override
	public Response<User> findOneByUsernameAndPassword(Request<User> request) {
		return new Response<>(iUserRepository.findOneByUsernameAndPassword(request.getBody().getUsername(), request.getBody().getPassword()));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Mapping(requestMethod = RequestMethod.GET)
	@Override
	public Response<User> findOne(Request<User> request) {
		return new Response<>(iUserRepository.findOne(request.getBody().getUserId()));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Mapping(requestMethod = RequestMethod.GET)
	@Override
	public Response<Iterable<User>> findAll(Request<User> request) {
		return new Response<>(iUserRepository.findAll());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Mapping(requestMethod = RequestMethod.GET)
	@Override
	public Response<Long> count(Request<User> request) {
		return new Response<>(iUserRepository.count());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Mapping(requestMethod = {RequestMethod.POST, RequestMethod.UPDATE})
	@Override
	public Response<User> save(Request<User> request) {
		return new Response<>(iUserRepository.save(request.getBody()));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Mapping(requestMethod = {RequestMethod.POST, RequestMethod.UPDATE})
	@Override
	public Response<Iterable<User>> saveAll(Request<Iterable<User>> request) {
		return new Response<>(iUserRepository.saveAll(request.getBody()));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Mapping(requestMethod = {RequestMethod.POST, RequestMethod.DELETE})
	@Override
	public Response<Void> deleteById(Request<User> request) {
		iUserRepository.deleteById(request.getBody().getUserId());
		return new Response<>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Mapping(requestMethod = {RequestMethod.POST, RequestMethod.DELETE})
	@Override
	public Response<Void> delete(Request<User> request) {
		iUserRepository.delete(request.getBody());
		return new Response<>();
	}
}
