package edu.wcsu.cs360.battleship.common.service;

import edu.wcsu.cs360.battleship.common.annotation.Inject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Service class used to inject dependencies into classes. Dependencies within classes are determined
 * with methods that have fields annotated with the {@link javax.inject.Inject} or
 * {@link edu.wcsu.cs360.battleship.common.annotation.Inject} annotations.
 */
public class DependencyInjectionService {

	private Map<String, Object> dependencyMap;
	private static final List<Class<? extends Annotation>> injectionAnnotationList;

	static {
		injectionAnnotationList = new ArrayList<>();
		injectionAnnotationList.add(Inject.class);
		injectionAnnotationList.add(javax.inject.Inject.class);
	}

	public DependencyInjectionService() {
		dependencyMap = new HashMap<>();
	}

	public void injectDependencies(Object object) throws IllegalArgumentException, IllegalAccessException {
		for (Class<? extends Annotation> annotation : injectionAnnotationList) {
			for (Field field : getFieldIterableWithAnnotation(annotation, object.getClass())) {
				if (dependencyMap.containsKey(getDependencyKey(field)))
					field.set(field, dependencyMap.get(getDependencyKey(field)));
				else
					field.set(field, dependencyMap.get(Character.toLowerCase(field.getClass().getName().charAt(0)) + field.getClass().getName().substring(1)));
			}
		}
	}

	public void registerDependency(Object object) {
		dependencyMap.put(Character.toLowerCase(object.getClass().getName().charAt(0)) + object.getClass().getName().substring(1), object);
	}

	public void registerDependency(String dependencyName, Object object) {
		dependencyMap.put(dependencyName, object);
	}

	private Iterable<Field> getFieldIterableWithAnnotation(Class<? extends Annotation> annotation, Class c) {
		List<Field> fieldList = new ArrayList<>();
		for (Field field : Arrays.asList(c.getFields())) {
			for (Annotation fieldAnnotation : field.getAnnotations()) {
				if (fieldAnnotation.getClass().equals(annotation))
					fieldList.add(field);
			}
		}
		return fieldList;
	}

	private String getDependencyKey(Field field) {
		try {
			Inject inject = field.getAnnotation(Inject.class);
			if (inject.value().length() > 0)
				return inject.value();
			else
				return field.getAnnotation(Inject.class).value();
		} catch (NullPointerException e) {
			return field.getAnnotation(Inject.class).value();
		}
	}

}
