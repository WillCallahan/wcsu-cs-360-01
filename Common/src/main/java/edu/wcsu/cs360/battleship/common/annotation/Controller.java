package edu.wcsu.cs360.battleship.common.annotation;

import edu.wcsu.cs360.battleship.common.domain.enumeration.RequestMethod;

import java.lang.annotation.*;

/**
 * Defines a class as a Controller
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Controller {
	
	/**
	 * Name of the Controller
	 * @return Name of the Controller
	 */
	String value() default "";
	
	/**
	 * Default {@link RequestMethod} of method handlers in the controller
	 * @return Default {@link RequestMethod} of method handlers in the controller
	 */
	RequestMethod requestMethod()[] default { RequestMethod.GET };

}
