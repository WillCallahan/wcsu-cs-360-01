package edu.wcsu.cs360.battleship.client.service.io;

/**
 * Used to call a method after a response has been received from a server
 * @param <T> Response type
 */
public interface IGameConnectionCallback<T> {
	
	void callback(T response);
	
}
