package com.lrb.saas.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.lrb.saas.core.enums.SAASInterfaceId;

@Target({ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Repeatable(SAASFields.class)
public @interface SAASField {
	/**
	 * Description of the property id.
	 */
	public String id() default "";

	/**
	 * 属性中文名
	 */
	public String name() default "";

	/**
	 * ui组件
	 */
	public String uiComponent() default "";
	
	/**
	 * ui属性，用json字符串来描述
	 * @return
	 */
	public String uiAttributes() default "";
	
	/**
	 * 字典url.
	 */
	public String dictionaryUrl() default "";

	/**
	 * 查询url
	 */
	public String getUrl() default "";

	/**
	 * 评论
	 */
	public String remark() default "";

	/**
	 * 对应接口id
	 */
	public SAASInterfaceId[] saasInterfaceIds() default SAASInterfaceId.ALL;
}
