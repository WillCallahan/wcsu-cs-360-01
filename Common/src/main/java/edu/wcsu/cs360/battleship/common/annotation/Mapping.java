package edu.wcsu.cs360.battleship.common.annotation;

import edu.wcsu.cs360.battleship.common.domain.enumeration.RequestMethod;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Mapping {

	String value() default "";
	RequestMethod requestMethod()[] default { RequestMethod.GET };

}
