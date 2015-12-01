package org.example.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.example.dao.BaseDao;
import org.example.dao.PaginationModel;
import org.example.dao.QueryResult;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository(value="baseDao")
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired(required=true)
	public HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Override
	public Serializable save(T o) {
		Serializable serializable = this.getHibernateTemplate().save(o);
		return serializable;
	}

	@Override
	public void delete(T o) {
		this.getHibernateTemplate().delete(o);
	}
	
	@Override
	public void delete(List<T> o) {
		for(T t:o){
			this.delete(t);
		}
	}

	@Override
	public void update(T o) {
		this.getHibernateTemplate().update(o);
	}

	@Override
	public void update(List<T> o) {
		for(T t:o){
			this.getHibernateTemplate().update(o);
		}
	}

	@Override
	public void saveOrUpdate(T o) {
		this.getHibernateTemplate().saveOrUpdate(o);
	}

	@Override
	public T get(Class<T> c, Serializable id) {
		return (T)this.getHibernateTemplate().get(c, id);
	}

	@Override
	public T get(String hql) {
		List<T> list = (List<T>) this.getHibernateTemplate().find(hql, null);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public T get(String hql, Map<String, Object> params) {
		List<String> paramNamesList = new ArrayList<String>();
		List<Object> paramValuesList = new ArrayList<Object>();
		
		Iterator iter = params.entrySet().iterator();
		while (iter.hasNext()) {
		    Map.Entry entry = (Map.Entry) iter.next();
		    Object key = entry.getKey();
		    paramNamesList.add(key.toString());
		    Object val = entry.getValue();
		    paramValuesList.add(val);
		}
		List<T> list = (List<T>) this.getHibernateTemplate().findByNamedParam(hql, (String[])paramNamesList.toArray(new String[paramNamesList.size()]), (String[])paramValuesList.toArray(new Object[paramValuesList.size()]));
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<T> find(String hql) {
		return (List<T>) this.getHibernateTemplate().find(hql, null);
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		List<T> list = new ArrayList<T>();
		List paramNamesList = new ArrayList();
		List paramValuesList = new ArrayList();
		
		Iterator iter = params.entrySet().iterator();
		while (iter.hasNext()) {
		    Map.Entry entry = (Map.Entry) iter.next();
		    Object key = entry.getKey();
		    paramNamesList.add(key);
		    Object val = entry.getValue();
		    paramValuesList.add(val);
		}
		list = (List<T>) this.getHibernateTemplate().findByNamedParam(hql, (String[])paramNamesList.toArray(new String[paramNamesList.size()]), (Object[])paramValuesList.toArray(new Object[paramValuesList.size()]));
		return list;
	}

	@Override
	public List<T> findByPage(Class clazz, String hql, int page, int rows) {
		return this.find(clazz, hql, (page - 1) * rows, rows);
	}

	@Override
	public List<T> find(Class clazz, String hql, int start, int length) {
		//查询需要的分页的属性
		DetachedCriteria criteria=DetachedCriteria.forClass(clazz);
		//criteria可以使用add()方法添加我们需要的查询条件，就好比SQL语句中的where条件语句
		//Restrictions类又很多的方法可以代替SQl语句中的“>、=、<、!”
		//criteria.add(Restrictions.eq("patype", 0));
		return (List<T>)this.getHibernateTemplate().findByCriteria(criteria, start, length);
	}

	@Override
	public List<T> findByPage(Class clazz, String hql, Map<String, Object> params, int page,
			int rows) {
		return this.find(clazz, hql, params, (page - 1) * rows, rows);
	}

	@Override
	public List<T> find(Class clazz, String hql, Map<String, Object> params, int start,
			int length) {
		DetachedCriteria criteria=DetachedCriteria.forClass(clazz);
		//获得map里面的信息
		Iterator iter = params.entrySet().iterator();
		while (iter.hasNext()) {
		    Map.Entry entry = (Map.Entry) iter.next();
		    Object key = entry.getKey();
		    Object val = entry.getValue();
		    criteria.add(Restrictions.eq(key.toString(), val));
		}
		return (List<T>) this.getHibernateTemplate().findByExample(criteria, start, length);
	}

	@Override
	public Long count(String hql) {
		return (long) this.count(hql,null);
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {
		return (long) this.find(hql, params).size();
	}

	@Override
	public int executeHql(String hql) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int executeHql(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
//		this.getHibernateTemplate().
		return 0;
	}

	@Override
	public int executeSQL(String sql) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void save(List<T> o) {
		for(T t:o){
			this.save(t);
		}
	}

	@Override
	public Map query(String functionName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map executeWithCondition(String procedureName,
			Map<String, Object> condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map executeByCondition(Class clazz, Map<String, Object> condition,
			String order, PaginationModel pagination, String procedureName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryResult<T> find2QueryResult(String hql,
			Map<String, Object> params, int start, int length) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryResult<T> find2QueryResultByPage(String hql,
			Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		Map<String, Object> params = new HashMap<String, Object>(); 
		params.put("userId", 1);
		Iterator iter = params.entrySet().iterator();
		while (iter.hasNext()) {
		    Map.Entry entry = (Map.Entry) iter.next();
		    Object key = entry.getKey();
		    Object val = entry.getValue();
		    System.out.println(key);
		    System.out.println(val.getClass().getName());
		}
	}
}
