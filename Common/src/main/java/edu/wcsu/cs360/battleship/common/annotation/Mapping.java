package edu.wcsu.cs360.battleship.common.annotation;

import edu.wcsu.cs360.battleship.common.domain.enumeration.RequestMethod;

import java.lang.annotation.*;

/**
 * Defines the types of requests that a controller method can handle
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Mapping {
	
	/**
	 * Name of the handler
	 *
	 * @return Name of the handler
	 */
	String value() default "";
	
	/**
	 * {@link RequestMethod} that the method can handle
	 *
	 * @return {@link RequestMethod} that the method can handle
	 */
	RequestMethod requestMethod()[] default {RequestMethod.GET};
	
}
