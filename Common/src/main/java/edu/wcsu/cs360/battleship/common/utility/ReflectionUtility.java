package edu.wcsu.cs360.battleship.common.utility;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility methods that perform Reflection
 */
public class ReflectionUtility {
	
	/**
	 * Gets all the classes defined in the type arguments of a generic class
	 * @param type Type get get Object arguments of
	 * @return Classes defined in the type arguments of a generic class
	 */
	public static List<Class<?>> getClassListFromGenericParametersType(Type type) {
		List<Class<?>> classList = new ArrayList<>();
		ParameterizedType parameterizedType = (ParameterizedType)type;
		Type[] types = parameterizedType.getActualTypeArguments();
		for (Type typeArgument : types)
			classList.add((Class<?>)typeArgument);
		return classList;
	}
	
	/**
	 * Gets the index of a Generic type in the {@link Method#getGenericParameterTypes()} of a method
	 * @param method Method to search through
	 * @param clazz Class of generic type
	 * @return Index of generic type class in {@link Method#getGenericParameterTypes()} or {@code -1} if no match exists
	 */
	public static int indexOfGenericTypeInMethodParameters(Method method, Class clazz) {
		for (int i = 0; i < method.getGenericParameterTypes().length; i++) {
			ParameterizedType parameterizedType = (ParameterizedType)method.getGenericParameterTypes()[i];
			if (parameterizedType.getRawType() == clazz)
				return i;
		}
		return -1;
	}
	
	/**
	 * Gets a list of {@link Class} as part of a matching generic class found in the {@link Method#getGenericParameterTypes()}
	 * of a {@link Method}. If the method does not have the {@link Class} provided as an argument, then {@code null} is returned.
	 * @param method Method to search through
	 * @param clazz Class to search in {@link Method#getGenericParameterTypes()}
	 * @return List of classes or null
	 */
	public static List<Class<?>> getClassListOfGenericTypeInMethodGenericParameterTypes(Method method, Class clazz) {
		int index = indexOfGenericTypeInMethodParameters(method, clazz);
		if (index >= 0)
			return getClassListFromGenericParametersType(method.getGenericParameterTypes()[index]);
		return null;
	}
	
	/**
	 * Gets the first {@link Class} from the {@link Method#getParameterTypes()} where the class is not found in the
	 * {@code clazzes} argument. If the {@link Method#getParameterTypes()} contains all classes in the {@code clazzes},
	 * then {@code null} is returned.
	 * @param method Method to get Parameter Classes from
	 * @param clazzes Classes to exclude
	 * @return First class of a method not in the clazzes argument or null
	 */
	public static Class getFirstClassOfMethodParametersByIsNotClass(Method method, Class... clazzes) {
		for (int i = 0; i < method.getParameterTypes().length; i++) {
			boolean classFound = false;
			for (int o = 0; o < clazzes.length; o++) {
				if (method.getParameterTypes()[i] == clazzes[o])
					classFound = true;
			}
			if (!classFound)
				return method.getParameterTypes()[i];
		}
		return null;
	}
	
}
