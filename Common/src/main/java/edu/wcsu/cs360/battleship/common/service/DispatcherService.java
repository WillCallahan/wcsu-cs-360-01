package edu.wcsu.cs360.battleship.common.service;

import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;
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
	
	/**
	 * Constructor
	 * @param dependencyInjectionService Dependency Injection Service containing require resolutions to dependencies
	 * @param controllerArray Classes to invoke and inject dependencies into upon request
	 */
	public DispatcherService(DependencyInjectionService dependencyInjectionService, Class... controllerArray) {
		this.dependencyInjectionService = dependencyInjectionService;
		this.registeredControllerList = new ArrayList<>();
		this.registeredControllerList.addAll(Arrays.asList(controllerArray));
	}

	@Override
	public Response dispatch(Request request) {
		if (request == null)
			throw new IllegalArgumentException();
		log.info("Dispatching request " + request.toString());
		String path[] = request.getTarget().split("\\.");
		if (path.length < 2)
			throw new IllegalArgumentException();
		for (Class c : registeredControllerList) {
			if (path[0].equals(Character.toLowerCase(c.getSimpleName().charAt(0)) + c.getSimpleName().substring(1))) {
				for (Method method : c.getMethods()) {
					if (method.getName().equals(path[1])) {
						try {
							Object object = c.newInstance();
							dependencyInjectionService.injectDependencies(object);
							return (Response) method.invoke(object);
						} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
							e.printStackTrace();
							return new Response("", 500, "Error: " + e.getMessage(), "");
						}
					}
				}
			}
		}
		throw new IllegalArgumentException("Target not found!");
	}

}
