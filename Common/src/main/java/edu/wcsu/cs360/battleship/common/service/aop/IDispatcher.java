package edu.wcsu.cs360.battleship.common.service.aop;

import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;

/**
 * Dispatches a {@link edu.wcsu.cs360.battleship.common.domain.socket.Request} to a {@link java.lang.reflect.Method}
 */
public interface IDispatcher {
	
	/**
	 * Dispatches {@link Request} to a matching {@link Request#method} of a {@link Request#target}
	 * @param request Request from opposing system
	 * @return Response of the Request
	 */
	Response dispatch(Request request, Object... knownObjects);

}
