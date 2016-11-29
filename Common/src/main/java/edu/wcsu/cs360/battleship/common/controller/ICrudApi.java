package edu.wcsu.cs360.battleship.common.controller;

import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;

import java.io.Serializable;

/**
 * Standard API Interface to perform CRUD operations defined in
 * {@link edu.wcsu.cs360.battleship.common.repository.ICrud}
 *
 * @param <T> Object to perform Crud operations upon
 */
public interface ICrudApi<T> {
	
	/**
	 * @param request Request from a client
	 * @return Response from API
	 * @see edu.wcsu.cs360.battleship.common.repository.ICrud#findOne(Serializable)
	 */
	Response<T> findOne(Request<T> request);
	
	/**
	 * @param request Request from a client
	 * @return Response from API
	 * @see edu.wcsu.cs360.battleship.common.repository.ICrud#findOne(Serializable)
	 */
	Response<Iterable<T>> findAll(Request<T> request);
	
	/**
	 * @param request Request from a client
	 * @return Response from API
	 * @see edu.wcsu.cs360.battleship.common.repository.ICrud#count()
	 */
	Response<Long> count(Request<T> request);
	
	/**
	 * @param request Request from a client
	 * @return Response from API
	 * @see edu.wcsu.cs360.battleship.common.repository.ICrud#save(Object)
	 */
	Response<T> save(Request<T> request);
	
	/**
	 * @param request Request from a client
	 * @return Response from API
	 * @see edu.wcsu.cs360.battleship.common.repository.ICrud#saveAll(Iterable)
	 */
	Response<Iterable<T>> saveAll(Request<Iterable<T>> request);
	
	/**
	 * @param request Request from a client
	 * @return Response from API
	 * @see edu.wcsu.cs360.battleship.common.repository.ICrud#deleteById(Serializable)
	 */
	Response<Void> deleteById(Request<T> request);
	
	/**
	 * @param request Request from a client
	 * @return Response from API
	 * @see edu.wcsu.cs360.battleship.common.repository.ICrud#delete(Object)
	 */
	Response<Void> delete(Request<T> request);
	
}
