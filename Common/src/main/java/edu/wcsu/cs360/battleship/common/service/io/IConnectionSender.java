package edu.wcsu.cs360.battleship.common.service.io;

import edu.wcsu.cs360.battleship.common.domain.socket.Request;

import java.util.concurrent.Future;

/**
 * Sends requests to listeners
 *
 * @param <T> Type of Request object
 * @param <U> Type of Response object
 */
public interface IConnectionSender<T, U> {
	
	/**
	 * Sends a {@link Request} to a listener
	 *
	 * @param request Request to send to a listener
	 * @return Response from the listener
	 */
	Future<U> send(T request);
	
}