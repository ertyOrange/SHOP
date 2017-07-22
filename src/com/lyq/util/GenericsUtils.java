package com.lyq.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericsUtils{
	//��ȡ���͵�����
	@SuppressWarnings("unchecked")	
	public static Class getGenericType(Class clazz){
		Type genType= clazz.getGenericSuperclass();
		 Type[] types= ((ParameterizedType)genType).getActualTypeArguments();
		 if(types[0] instanceof Class){
			 return Object.class;
		 }
		 
		return (Class)types[0];
	}
	
	//��ȡ���������
	public static String getGenericName(Class clazz){
		return clazz.getSimpleName();
	}
}