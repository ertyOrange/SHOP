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

	// ����
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
						.append(" ")//��ӿո�
						.append(where == null ? "" : where)
						.append(creatOrderBy(orderby))//���whereΪnull����ӿո�,��֮���where
						.toString();
				Query query= session.createQuery(hql);
				setQueryParams(query, queryParams);
				List<T> list = null;//����List����
				// ���maxResult<0�����ѯ����
				if(maxResult < 0 && pageNo < 0){
					list = query.list();//����ѯ���ת��ΪList����
				}else{
					list = query.setFirstResult(getFirstResult(pageNo, maxResult))//���÷�ҳ��ʼλ��
								.setMaxResults(maxResult)//����ÿҳ��ʾ�ļ�¼��
								.list();//����ѯ���ת��ΪList����
					//�����ѯ�ܼ�¼����hql���
					hql = new StringBuffer().append("select count(*) from ")//���hql���
									.append(GenericsUtils.getGenericName(entityClass))//��Ӷ�������
									.append(" ")//��ӿո�
									.append(where == null ? "" : where)//���whereΪnull����ӿո�,��֮���where
									.toString();//ת��Ϊ�ַ���
					query = session.createQuery(hql);//ִ�в�ѯ
					setQueryParams(query,queryParams);//����hql����
					int totalRecords = ((Long) query.uniqueResult()).intValue();//����ת��
					pageModel.setTotalRecords(totalRecords);//�����ܼ�¼��
				}
				pageModel.setList(list);//����ѯ��list�������ʵ�������
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
