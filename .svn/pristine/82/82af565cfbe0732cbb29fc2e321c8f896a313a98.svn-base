package com.lyq.util.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

public class EnumType<T extends Enum<T>> implements UserType {
	// 生成数据库数据类型
	private static final int[] SQL_TYPES = { Types.VARCHAR };

	// class
	private Class<T> clazz = null;

	// 构造方法
	public EnumType(Class<T> c) {
		this.clazz = c;
	}

	@Override
	public Object assemble(Serializable arg0, Object arg1) throws HibernateException {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public Object deepCopy(Object arg0) throws HibernateException {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public Serializable disassemble(Object arg0) throws HibernateException {
		// TODO Auto-generated method stub
		return (Serializable) arg0;
	}

	@Override
	public boolean equals(Object arg0, Object arg1) throws HibernateException {
		if (arg0 == arg1)
			return true;
		if (null == arg0 || null == arg1)
			return true;
		return arg0.equals(arg1);
	}

	@Override
	public int hashCode(Object arg0) throws HibernateException {
		return arg0.hashCode();
	
	}

	@Override
	public boolean isMutable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object nullSafeGet(ResultSet arg0, String[] arg1, Object arg2) throws HibernateException, SQLException {
		try {
			String name= arg0.getString(arg1[0]).toUpperCase();
			
			return Enum.valueOf(clazz, name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public void nullSafeSet(PreparedStatement arg0, Object arg1, int arg2) throws HibernateException, SQLException {

		if(null==arg1){
			arg0.setNull(arg2, Types.VARCHAR);
		}else{
			arg0.setString(arg2, ((Enum) arg1).name());
		}
	}

	@Override
	public Object replace(Object arg0, Object arg1, Object arg2) throws HibernateException {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public Class returnedClass() {
		// TODO Auto-generated method stub
		return clazz;
	}

	@Override
	public int[] sqlTypes() {
		// TODO Auto-generated method stub
		return SQL_TYPES;
	}

}
