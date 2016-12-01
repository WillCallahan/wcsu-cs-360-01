package edu.wcsu.cs360.battleship.common.repository;

import edu.wcsu.cs360.battleship.common.domain.socket.Response;

import java.io.Serializable;
import java.util.concurrent.Future;

/**
 * Standard Interface for accessing an {@link edu.wcsu.cs360.battleship.common.controller.ICrudApi}
 *
 * @param <T> Model to Access
 * @param <ID> Type of the Primary key of the model
 * @see edu.wcsu.cs360.battleship.common.controller.ICrudApi
 */
public interface ICrudFuture<T, ID extends Serializable> {
	
	/**
	 * @return Response from the server in the future
	 * @see edu.wcsu.cs360.battleship.common.repository.ICrud#findOne(Serializable)
	 */
	Future<Response> findOne(ID id);
	
	/**
	 * @return Response from the server in the future
	 * @see edu.wcsu.cs360.battleship.common.repository.ICrud#findOne(Serializable)
	 */
	Future<Response> findAll();
	
	/**
	 * @return Response from the server in the future
	 * @see edu.wcsu.cs360.battleship.common.repository.ICrud#count()
	 */
	Future<Response> count();
	
	/**
	 * @return Response from the server in the future
	 * @see edu.wcsu.cs360.battleship.common.repository.ICrud#save(Object)
	 */
	Future<Response> save(T type);
	
	/**
	 * @return Response from the server in the future
	 * @see edu.wcsu.cs360.battleship.common.repository.ICrud#saveAll(Iterable)
	 */
	Future<Response> saveAll(Iterable<T> typeList);
	
	/**
	 * @return Response from the server in the future
	 * @see edu.wcsu.cs360.battleship.common.repository.ICrud#deleteById(Serializable)
	 */
	Future<Response> deleteById(ID id);
	
	/**
	 * @return Response from the server in the future
	 * @see edu.wcsu.cs360.battleship.common.repository.ICrud#delete(Object)
	 */
	Future<Response> delete(T type);
	
}
