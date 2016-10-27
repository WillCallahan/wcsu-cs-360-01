package edu.wcsu.cs360.battleship.common.service;

import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;

/**
 * Dispatches a {@link edu.wcsu.cs360.battleship.common.domain.socket.Request} to a method
 */
public interface IDisdpatecher {

	Response dispatch(Request request);

}
