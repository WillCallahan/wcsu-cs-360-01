package edu.wcsu.cs360.battleship.common.service;

import edu.wcsu.cs360.battleship.common.domain.socket.Request;
import edu.wcsu.cs360.battleship.common.domain.socket.Response;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DispatcherService implements IDispatcher {
	private DependencyInjectionService dependencyInjectionService;
	private List<Class> registeredControllerList;

	public DispatcherService(DependencyInjectionService dependencyInjectionService, Class... controllerArray) {
		this.dependencyInjectionService = dependencyInjectionService;
		this.registeredControllerList = new ArrayList<>();
		this.registeredControllerList.addAll(Arrays.asList(controllerArray));
	}

	@Override
	public Response dispatch(Request request) {
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
