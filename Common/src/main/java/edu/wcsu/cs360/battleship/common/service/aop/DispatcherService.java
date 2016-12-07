package edu.wcsu.cs360.battleship.common.service.aop;

import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;
import edu.wcsu.cs360.battleship.common.service.di.DependencyInjectionService;
import edu.wcsu.cs360.battleship.common.service.serialize.IClassCastService;
import edu.wcsu.cs360.battleship.common.service.serialize.ObjectMapperClassCastService;
import edu.wcsu.cs360.battleship.common.utility.ArrayUtility;
import edu.wcsu.cs360.battleship.common.utility.ReflectionUtility;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Dispatches {@link Request} to a {@link Method} in {@link DispatcherService#registeredControllerList} based on the
 * name of the {@link Class} in the {@link DispatcherService#registeredControllerList} and the name of the {@link Method}
 * in the {@link DispatcherService#registeredControllerList}. For each {@link DispatcherService#registeredControllerList},
 * dependencies are injected using a {@link DependencyInjectionService}
 */
public class DispatcherService implements IDispatcher {
	
	private Log log = LogFactory.getLog(this.getClass());
	private DependencyInjectionService dependencyInjectionService;
	private List<Class> registeredControllerList;
	private List<IClassCastService> iClassCastServicesList;
	
	/**
	 * Constructor
	 *
	 * @param dependencyInjectionService Dependency Injection Service containing require resolutions to dependencies
	 * @param controllerArray            Classes to invoke and inject dependencies into upon request
	 */
	public DispatcherService(DependencyInjectionService dependencyInjectionService, Class... controllerArray) {
		this.dependencyInjectionService = dependencyInjectionService;
		this.registeredControllerList = new ArrayList<>();
		this.registeredControllerList.addAll(Arrays.asList(controllerArray));
		this.iClassCastServicesList = new ArrayList<>();
		this.iClassCastServicesList.add(new ObjectMapperClassCastService());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response dispatch(Request request, Object... knownObjects) {
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
							Object[] arguments = knownObjects;
							arguments = ArrayUtility.merge(request, Object.class, arguments);
							arguments = ArrayUtility.merge(request.getBody(), Object.class, arguments);
							return (Response) method.invoke(object, getArguments(method, arguments));
						} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
							log.error("Unhandled error: ", e);
							return new Response<>("", 500, "Error: " + e.getMessage(), null);
						}
					}
				}
			}
		}
		throw new IllegalArgumentException("Target not found!");
	}
	
	/**
	 * Gets the arguments to be provided to a method dynamically.
	 *
	 * @param method       Method to populate arguments of
	 * @param knownObjects Objects to include in possible object arguments
	 * @return Ordered argument array to be passed to the {@link Method}
	 */
	protected Object[] getArguments(Method method, Object... knownObjects) {
		List<Object> objectList = new ArrayList<>();
		for (int i = 0; i < method.getParameterTypes().length; i++) {
			log.trace("Searching for instances of " + method.getParameterTypes()[i]);
			Object foundObject = null;
			for (Object knownObject : knownObjects) { //Try to provide argument from known objects by explicit type
				if (method.getParameterTypes()[i] == knownObject.getClass()) {
					foundObject = knownObject;
					break;
				}
			}
			if (foundObject == null) { //Try to provide argument from dependency injection service
				for (Object object : dependencyInjectionService.getDependencyList()) {
					if (method.getParameterTypes()[i] == object.getClass()) {
						foundObject = object;
						break;
					}
				}
			}
			if (foundObject == null) { //Try to provide argument from inherited classes in known objects
				for (Object knownObject : knownObjects) {
					if (method.getParameterTypes()[i].isAssignableFrom(knownObject.getClass())) {
						foundObject = knownObject;
						break;
					}
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
	 * Attempts to cast the {@link Request#body} to an explicit {@link java.lang.reflect.Type} based on the arguments
	 * the in {@link Method} argument.
	 *
	 * @param method  Method get gather type object information from
	 * @param request Request Object with casted {@link Request#body} if a matching method argument was found
	 */
	@SuppressWarnings("unchecked")
	protected void tryCastRequestType(Method method, Request request) {
		Class<?> clazz = null;
		List<Class<?>> classList = ReflectionUtility.getClassListOfGenericTypeInMethodGenericParameterTypes(method, Request.class);
		if (classList != null)
			clazz = classList.get(0);
		if (clazz == null)
			clazz = ReflectionUtility.getFirstClassOfMethodParametersByIsNotClass(method, Request.class);
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
		} else
			log.warn("Object class of Request body not found!");
	}
	
}
