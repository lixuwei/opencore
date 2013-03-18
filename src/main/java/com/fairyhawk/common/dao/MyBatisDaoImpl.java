package com.fairyhawk.common.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class  MyBatisDaoImpl extends SqlSessionDaoSupport implements MyBatisDao{
	
	public int insert(String sqlKey, Object object) {
		return (Integer) this.getSqlSession().insert(sqlKey, object);
	}

	public int delete(String sqlKey, Object object) {
		return this.getSqlSession().delete(sqlKey, object);
	}
	
	public int update(String key, Object object) {
		return getSqlSession().update(key, object);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T selectOne(String sqlKey, Object params) {
		return (T) this.getSqlSession().selectOne(sqlKey, params);
	}
	
	public <T> List<T> selectList(String sqlKey, Object params) {
		return this.getSqlSession().selectList(sqlKey, params);
	}
    
    
}
