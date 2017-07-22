package com.lyq.dao;

import java.io.Serializable;
import java.util.Map;

import com.lyq.model.PageModel;

public interface BaseDao <T>{
	//基本数据库操作
	public void Save(Object obj);
	
	public void saveOrUpdate(Object obj);
	
	public void update(Object obj);
	
	public void delete(Serializable ...ids);
	
	public T get(Serializable entityId);
	
	public T load(Serializable entityId);
	
	public Object uniqueResult(String hql ,Object[] queryParams);//使用HQL语句操作
	
	//分页操作
	public long getCount();
	
	//普通的分页操作
	public PageModel<T> find(int pageNo,int maxResult);
	
	//搜索信息分页方法
	public PageModel<T> find(int pageNo,int maxResult,String where ,Object [] queryParams);
	
	//按指定的条件配需分页方法
	public PageModel<T> find(int pageNo, int maxResult,Map<String, String> orderby);
	
	//按指定条件分页和排序的分页方法
	public PageModel<T> find(String where ,Object[] queryParams,Map<String ,String> orderby ,int pageNo,int maxResult);
}
