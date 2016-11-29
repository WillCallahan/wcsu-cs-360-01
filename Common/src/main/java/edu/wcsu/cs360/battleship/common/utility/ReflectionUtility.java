package edu.wcsu.cs360.battleship.common.utility;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtility {
	
	public static List<Class<?>> getClassFromGenericParametersType(Type type) {
		List<Class<?>> classList = new ArrayList<>();
		ParameterizedType parameterizedType = (ParameterizedType)type;
		Type[] types = parameterizedType.getActualTypeArguments();
		for (Type typeArgument : types)
			classList.add((Class<?>)typeArgument);
		return classList;
	}
	
	public static int indexOfGenericTypeInMethodParameters(Method method, Class clazz) {
		for (int i = 0; i < method.getGenericParameterTypes().length; i++) {
			ParameterizedType parameterizedType = (ParameterizedType)method.getGenericParameterTypes()[i];
			if (parameterizedType.getRawType() == clazz)
				return i;
		}
		return -1;
	}
	
	public static List<Class<?>> getClassListOfGenericTypeInMethodGenericParameters(Method method, Class clazz) {
		int index = indexOfGenericTypeInMethodParameters(method, clazz);
		if (index >= 0)
			return getClassFromGenericParametersType(method.getGenericParameterTypes()[index]);
		return null;
	}
	
}
