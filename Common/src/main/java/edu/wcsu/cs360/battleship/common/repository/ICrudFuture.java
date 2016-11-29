package edu.wcsu.cs360.battleship.common.repository;

import edu.wcsu.cs360.battleship.common.domain.socket.Response;

import java.io.Serializable;
import java.util.concurrent.Future;

/**
 * Standard Interface for accessing an {@link edu.wcsu.cs360.battleship.common.controller.ICrudApi}
 *
 * @param <T> Object to perform Crud operations upon
 * @see edu.wcsu.cs360.battleship.common.controller.ICrudApi
 */
public interface ICrudFuture<T> {
	
	/**
	 * @param request Request to send to a server
	 * @return Response from the server in the future
	 * @see edu.wcsu.cs360.battleship.common.repository.ICrud#findOne(Serializable)
	 */
	Future<Response> findOne(T request);
	
	/**
	 * @param request Request to send to a server
	 * @return Response from the server in the future
	 * @see edu.wcsu.cs360.battleship.common.repository.ICrud#findOne(Serializable)
	 */
	Future<Response> findAll(T request);
	
	/**
	 * @param request Request to send to a server
	 * @return Response from the server in the future
	 * @see edu.wcsu.cs360.battleship.common.repository.ICrud#count()
	 */
	Future<Response> count(T request);
	
	/**
	 * @param request Request to send to a server
	 * @return Response from the server in the future
	 * @see edu.wcsu.cs360.battleship.common.repository.ICrud#save(Object)
	 */
	Future<Response> save(T request);
	
	/**
	 * @param request Request to send to a server
	 * @return Response from the server in the future
	 * @see edu.wcsu.cs360.battleship.common.repository.ICrud#saveAll(Iterable)
	 */
	Future<Response> saveAll(Iterable<T> request);
	
	/**
	 * @param request Request to send to a server
	 * @return Response from the server in the future
	 * @see edu.wcsu.cs360.battleship.common.repository.ICrud#deleteById(Serializable)
	 */
	Future<Response> deleteById(T request);
	
	/**
	 * @param request Request to send to a server
	 * @return Response from the server in the future
	 * @see edu.wcsu.cs360.battleship.common.repository.ICrud#delete(Object)
	 */
	Future<Response> delete(T request);
	
}
