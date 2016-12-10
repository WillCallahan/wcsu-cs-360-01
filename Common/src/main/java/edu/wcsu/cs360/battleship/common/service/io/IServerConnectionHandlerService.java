package edu.wcsu.cs360.battleship.common.service.io;

import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;

/**
 * Implements the {@link IConnectionSender} to define the generic type parameters as {@link Request} and {@link Response}
 */
public interface IServerConnectionHandlerService extends IConnectionSender<Request, Response> {
	
}
