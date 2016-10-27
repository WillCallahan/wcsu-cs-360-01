package edu.wcsu.cs360.battleship.common.annotation;

import java.lang.annotation.*;

/**
 * Used to mark a field of an object as a field that should be injected by a dependency injection provider
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Inject {

	/**
	 * Name of the dependency to inject
	 */
	String value() default "";

}
