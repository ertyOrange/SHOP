package com.lyq.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericsUtils{
	//获取泛型的类型
	@SuppressWarnings("unchecked")	
	public static Class getGenericType(Class clazz){
		Type genType= clazz.getGenericSuperclass();
		 Type[] types= ((ParameterizedType)genType).getActualTypeArguments();
		 if(types[0] instanceof Class){
			 return Object.class;
		 }
		 
		return (Class)types[0];
	}
	
	//获取对象的名称
	public static String getGenericName(Class clazz){
		return clazz.getSimpleName();
	}
}