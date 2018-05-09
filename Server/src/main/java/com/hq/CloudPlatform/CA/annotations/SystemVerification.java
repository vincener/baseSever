package com.hq.CloudPlatform.CA.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//@Target({ ElementType.PARAMETER, ElementType.METHOD ,ElementType.FIELD,ElementType.TYPE})
@Target({ ElementType.PARAMETER, ElementType.METHOD ,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemVerification {
	
	String description() default "";

	SystemVerificationType type() default SystemVerificationType.ADD;
}
