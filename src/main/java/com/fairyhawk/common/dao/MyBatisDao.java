package com.fairyhawk.common.dao;

import java.util.List;

public interface MyBatisDao  {
	
	public int insert(String sqlKey, Object object);

	public int delete(String sqlKey, Object object) ;

	public int update(String sqlKey, Object object) ;
	
	public <T> T selectOne(String sqlKey, Object params) ;
	
	public <T> List<T> selectList(String sqlKey, Object params);

}
