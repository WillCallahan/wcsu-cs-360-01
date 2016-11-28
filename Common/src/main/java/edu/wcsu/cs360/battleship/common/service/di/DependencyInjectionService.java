package edu.wcsu.cs360.battleship.common.service.di;

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
				field.setAccessible(true);
				if (dependencyMap.containsKey(getAnnotationDependencyKey(field)))
					field.set(object, dependencyMap.get(getAnnotationDependencyKey(field)));
				else
					field.set(object, dependencyMap.get(Character.toLowerCase(field.getType().getSimpleName().charAt(0)) + field.getType().getSimpleName().substring(1)));
			}
		}
	}

	public void registerDependency(Object object) {
		dependencyMap.put(Character.toLowerCase(object.getClass().getSimpleName().charAt(0)) + object.getClass().getSimpleName().substring(1), object);
	}

	public <T> void registerDependency(Class<T> c, T object) {
		dependencyMap.put(Character.toLowerCase(c.getSimpleName().charAt(0)) + c.getSimpleName().substring(1), object);
	}

	public void registerDependency(String dependencyName, Object object) {
		dependencyMap.put(dependencyName, object);
	}

	private Iterable<Field> getFieldIterableWithAnnotation(Class<? extends Annotation> annotation, Class c) {
		List<Field> fieldList = new ArrayList<>();
		for (Field field : Arrays.asList(c.getDeclaredFields())) {
			for (Annotation fieldAnnotation : field.getAnnotations()) {
				if (fieldAnnotation.annotationType().equals(annotation))
					fieldList.add(field);
			}
		}
		return fieldList;
	}

	private String getAnnotationDependencyKey(Field field) {
		try {
			Inject inject = field.getAnnotation(Inject.class);
			if (inject.value().length() > 0)
				return inject.value();
			else
				return field.getName();
		} catch (NullPointerException e) {
			return field.getName();
		}
	}
	
	public Iterable<Object> getDependencyList() {
		return dependencyMap.values();
	}

}
