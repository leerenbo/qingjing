package com.lrb.saas.framework.util;

public class ClassUtil {

	@SuppressWarnings("rawtypes")
	public static Class getInnerClass(Class outClass, String innerClassName) throws ClassNotFoundException {
		return outClass.getClassLoader().loadClass(outClass.getName() + "$" + innerClassName);
	}

}
