package edu.wcsu.cs360.battleship.common.service;

public interface IClassCastService {
	
	/**
	 * Attempts to cast an {@link Object} to a {@link Class} where the value of the class is derived from the
	 * {@link Object#toString()} method.
	 *
	 * @param object Object to cast
	 * @param clazz Class to cast object to
	 * @param <T> Type to be casted to
	 * @return Casted object or null if argument is null
	 */
	<T> T cast(Object object, Class<T> clazz);
	
}
