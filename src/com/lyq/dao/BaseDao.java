package com.lyq.dao;

import java.io.Serializable;
import java.util.Map;

import com.lyq.model.PageModel;

public interface BaseDao <T>{
	//�������ݿ����
	public void Save(Object obj);
	
	public void saveOrUpdate(Object obj);
	
	public void update(Object obj);
	
	public void delete(Serializable ...ids);
	
	public T get(Serializable entityId);
	
	public T load(Serializable entityId);
	
	public Object uniqueResult(String hql ,Object[] queryParams);//ʹ��HQL������
	
	//��ҳ����
	public long getCount();
	
	//��ͨ�ķ�ҳ����
	public PageModel<T> find(int pageNo,int maxResult);
	
	//������Ϣ��ҳ����
	public PageModel<T> find(int pageNo,int maxResult,String where ,Object [] queryParams);
	
	//��ָ�������������ҳ����
	public PageModel<T> find(int pageNo, int maxResult,Map<String, String> orderby);
	
	//��ָ��������ҳ������ķ�ҳ����
	public PageModel<T> find(String where ,Object[] queryParams,Map<String ,String> orderby ,int pageNo,int maxResult);
}
