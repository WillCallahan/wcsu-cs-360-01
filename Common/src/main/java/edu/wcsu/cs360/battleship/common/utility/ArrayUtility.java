package edu.wcsu.cs360.battleship.common.utility;

import java.lang.reflect.Array;

/**
 * Utility methods for working with Arrays
 */
public class ArrayUtility {
	
	/**
	 * Merges an object into an array
	 *
	 * @param argument Argument to add to array
	 * @param variableArguments Array or variable arguments to merge
	 * @return Array combines with {@code argument}
	 */
	public static <T> T[] merge(T argument, Class<T> clazz, T... variableArguments) {
		@SuppressWarnings("unchecked")
		T[] newArray = (T[])Array.newInstance(clazz, variableArguments.length + 1);
		newArray[0] = argument;
		System.arraycopy(variableArguments, 0, newArray, 1, variableArguments.length);
		return newArray;
	}
	
}
