package com.lyq.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyq.model.PageModel;
import com.lyq.util.GenericsUtils;

@Transactional
@SuppressWarnings("unchecked")
public class DaoSuport<T> implements BaseDao<T> {

	protected Class<T> entityClass = GenericsUtils.getGenericType(this.getClass());

	@Autowired
	protected HibernateTemplate template;

	public HibernateTemplate getTemplate() {
		return template;
	}

	// 保存
	@Override
	public void Save(Object obj) {
		getTemplate().save(obj);

	}

	@Override
	public void saveOrUpdate(Object obj) {
		getTemplate().saveOrUpdate(obj);

	}

	@Override
	public void update(Object obj) {
		getTemplate().update(obj);

	}

	@Override
	public void delete(Serializable... ids) {
		for (Serializable id : ids) {
			T t = (T) getTemplate().load(entityClass, id);
			getTemplate().delete(t);
		}

	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public T get(Serializable entityId) {
		return (T) getTemplate().get(this.entityClass, entityId);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public T load(Serializable entityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Object uniqueResult(final String hql, final Object[] queryParams) {
		return getTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
				Query query= arg0.createQuery(hql);
				setQueryParams(query, queryParams);
				return query.uniqueResult();
			}
		});
	}

	protected void setQueryParams(Query query, Object[] queryParams) {
		if(queryParams!=null &&queryParams.length>0){
			for(int i=0;i<queryParams.length;i++){
				query.setParameter(i, queryParams[i]);
			}
		}
		
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED, readOnly=true)
	public long getCount() {
		String hql="select count(*) from "+GenericsUtils.getGenericType(this.entityClass);
		return (Long) uniqueResult(hql, null);
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public PageModel<T> find(int pageNo, int maxResult) {
		return find(null,null,null,pageNo, maxResult);
	}



	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public PageModel<T> find(int pageNo, int maxResult, Map<String, String> orderby) {
		// TODO Auto-generated method stub
		 return find(null,null,orderby,pageNo,maxResult);
	}


	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public PageModel<T> find(int pageNo, int maxResult, String where, Object[] queryParams) {
		// TODO Auto-generated method stub
		 return find(where, queryParams, null, pageNo, maxResult);
	}
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public PageModel<T> find(final String where, final Object[] queryParams, final Map<String, String> orderby, final int pageNo,
			final int maxResult) {
		final PageModel<T> pageModel= new PageModel<T>();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(maxResult);
		getTemplate().execute(new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql=new StringBuffer().append("from")
						.append(GenericsUtils.getGenericName(entityClass))
						.append(" ")//添加空格
						.append(where == null ? "" : where)
						.append(creatOrderBy(orderby))//如果where为null就添加空格,反之添加where
						.toString();
				Query query= session.createQuery(hql);
				setQueryParams(query, queryParams);
				List<T> list = null;//定义List对象
				// 如果maxResult<0，则查询所有
				if(maxResult < 0 && pageNo < 0){
					list = query.list();//将查询结果转化为List对象
				}else{
					list = query.setFirstResult(getFirstResult(pageNo, maxResult))//设置分页起始位置
								.setMaxResults(maxResult)//设置每页显示的记录数
								.list();//将查询结果转化为List对象
					//定义查询总记录数的hql语句
					hql = new StringBuffer().append("select count(*) from ")//添加hql语句
									.append(GenericsUtils.getGenericName(entityClass))//添加对象类型
									.append(" ")//添加空格
									.append(where == null ? "" : where)//如果where为null就添加空格,反之添加where
									.toString();//转化为字符串
					query = session.createQuery(hql);//执行查询
					setQueryParams(query,queryParams);//设置hql参数
					int totalRecords = ((Long) query.uniqueResult()).intValue();//类型转换
					pageModel.setTotalRecords(totalRecords);//设置总记录数
				}
				pageModel.setList(list);//将查询的list对象放入实体对象中
				return null;
			}
		});
		return pageModel;
	}

	protected int getFirstResult(int pageNo, int maxResult) {
		int firstResult= (pageNo-1)*maxResult;
		return firstResult <0 ? 0:firstResult;
	}

	protected Object creatOrderBy(Map<String, String> orderby) {
		StringBuffer sb= new StringBuffer("");
		if(orderby!=null&&orderby.size()>0){
			sb.append("order by");
			for(String key:orderby.keySet()){
				sb.append(key).append(" ").append(orderby.get(key)).append(",");
			}
		}
		return sb.deleteCharAt(sb.length()-1);
	}

}
