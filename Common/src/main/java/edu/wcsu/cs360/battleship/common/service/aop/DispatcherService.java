package edu.wcsu.cs360.battleship.common.service.aop;

import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;
import edu.wcsu.cs360.battleship.common.service.di.DependencyInjectionService;
import edu.wcsu.cs360.battleship.common.service.serialize.IClassCastService;
import edu.wcsu.cs360.battleship.common.service.serialize.ObjectMapperClassCastService;
import edu.wcsu.cs360.battleship.common.utility.ReflectionUtility;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DispatcherService implements IDispatcher {
	
	private Log log = LogFactory.getLog(this.getClass());
	private DependencyInjectionService dependencyInjectionService;
	private List<Class> registeredControllerList;
	private List<IClassCastService> iClassCastServicesList;
	
	/**
	 * Constructor
	 * @param dependencyInjectionService Dependency Injection Service containing require resolutions to dependencies
	 * @param controllerArray Classes to invoke and inject dependencies into upon request
	 */
	public DispatcherService(DependencyInjectionService dependencyInjectionService, Class... controllerArray) {
		this.dependencyInjectionService = dependencyInjectionService;
		this.registeredControllerList = new ArrayList<>();
		this.registeredControllerList.addAll(Arrays.asList(controllerArray));
		this.iClassCastServicesList = new ArrayList<>();
		this.iClassCastServicesList.add(new ObjectMapperClassCastService());
	}

	@Override
	public Response dispatch(Request request) {
		if (request == null)
			throw new IllegalArgumentException();
		log.info("Dispatching request " + request.toString());
		String path[] = request.getTarget().split("\\.");
		if (path.length < 2)
			throw new IllegalArgumentException();
		for (Class clazz : registeredControllerList) {
			if (path[0].equals(Character.toLowerCase(clazz.getSimpleName().charAt(0)) + clazz.getSimpleName().substring(1))) {
				for (Method method : clazz.getMethods()) {
					if (method.getName().equals(path[1])) {
						tryCastRequestType(method, request);
						try {
							Object object = clazz.newInstance();
							dependencyInjectionService.injectDependencies(object);
							return (Response) method.invoke(object, getArguments(method, request, request.getBody()));
						} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
							e.printStackTrace();
							return new Response<>("", 500, "Error: " + e.getMessage(), null);
						}
					}
				}
			}
		}
		throw new IllegalArgumentException("Target not found!");
	}
	
	protected Object[] getArguments(Method method, Object... knownObjects) {
		List<Object> objectList = new ArrayList<>();
		for (int i = 0; i < method.getParameterTypes().length; i++) {
			log.trace("Searching for instances of " + method.getParameterTypes()[i]);
			Object foundObject = null;
			for (Object knownObject : knownObjects) {
				if (method.getParameterTypes()[i] == knownObject.getClass()) {
					foundObject = knownObject;
					break;
				}
			}
			for (Object object : dependencyInjectionService.getDependencyList()) {
				if (method.getParameterTypes()[i] == object.getClass()) {
					foundObject = object;
					break;
				}
			}
			if (foundObject == null)
				throw new IllegalArgumentException("Argument " + i + " instance for method " + method.toString() + " of " + method.getDeclaringClass().toString() + " not found!");
			else {
				objectList.add(foundObject);
				log.trace("Argument " + i + " instance for method " + method.toString() + " found: " + foundObject);
			}
		}
		return objectList.toArray();
	}
	
	/**
	 * Gets the first {@link Class} from the {@link Method#getParameterTypes()} where the class is not found in the
	 * {@code clazzes} argument. If the {@link Method#getParameterTypes()} contains all classes in the {@code clazzes},
	 * then {@code null} is returned.
	 * @param method Method to get Parameter Classes from
	 * @param clazzes Classes to exclude
	 * @return First class of a method not in the clazzes argument or null
	 */
	protected Class getClassOfMethodParameterByIsNotClass(Method method, Class... clazzes) {
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
	
	protected void tryCastRequestType(Method method, Request request) {
		Class<?> clazz = getClassOfMethodParameterByIsNotClass(method, Request.class);
		if (clazz == null) {
			List<Class<?>> classList = ReflectionUtility.getClassListOfGenericTypeInMethodGenericParameters(method, Request.class);
			if (classList != null)
				clazz = classList.get(0);
		}
		if (clazz != null) {
			log.debug("Object class of Request body: " + clazz);
			for (IClassCastService iClassCastService : iClassCastServicesList) {
				try {
					request.setBody(iClassCastService.cast(request.getBody(), clazz));
					log.debug("Request object body successfully casted to " + clazz);
				} catch (Exception e) {
					log.debug("Unable to cast request object body to " + clazz, e);
				}
			}
		}
		else
			log.warn("Object class of Request body not found!");
	}

}
