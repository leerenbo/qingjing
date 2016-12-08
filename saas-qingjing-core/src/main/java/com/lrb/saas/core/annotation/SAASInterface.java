package com.lrb.saas.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.lrb.saas.core.enums.SAASInterfaceId;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Repeatable(SAASInterfaces.class)
public @interface SAASInterface {

	public SAASInterfaceId id() default SAASInterfaceId.DEFAULT;

	public String remark() default "";

	public String name() default "";

}
