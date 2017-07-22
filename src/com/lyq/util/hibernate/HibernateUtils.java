package com.lyq.util.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static SessionFactory factory=null;
	
	private static final ThreadLocal<Session> threadLocal =new ThreadLocal<Session>();
	
	private static Configuration cfg=new Configuration();
	
	static {
		try {
			cfg.configure();
			factory= cfg.buildSessionFactory();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static Session getSession(){
		Session session= threadLocal.get();
		
		if(session==null||!session.isOpen()){
			if(factory==null){
				rebuildSessionFactory();
			}
		}
		session=(factory!=null) ?factory.openSession():null;
		return session;
	}
	public static void rebuildSessionFactory() {
		try {
			// ��Ĭ�ϵĵط�calsspath�м���Hibernate�����ļ�
			cfg.configure();
			// ʵ����SessionFactory
			factory = cfg.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace(); // ��ӡ�쳣��Ϣ
		}
	}
	public static SessionFactory getFactory() {
		return factory;
	}

	public static void closeSession(){
		Session session= threadLocal.get();
		threadLocal.remove();
		
		if(session!=null){
			if(session.isOpen()){
				session.close();
			}
		}
	}
	
	
	
}
