package edu.wcsu.cs360.battleship.common.utility;

import edu.wcsu.cs360.battleship.common.domain.socket.Response;

/**
 * Utility classes for a {@link Response}
 */
public class ResponseUtility {
	
	/**
	 * Checks if the {@link Response#statusCode} represents an error response
	 *
	 * @param response Response object to check for error
	 * @return Whether or not Response has an error
	 */
	public static boolean hasError(Response response) {
		if (response.getStatusCode() >= 400 || response.getStatusCode() == 0)
			return true;
		return false;
	}
	
}
