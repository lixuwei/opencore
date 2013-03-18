package com.fairyhawk.common.db;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
 * @ClassName  DataSourceRouter
 * @package com.fairyhawk.common.db
 * @description 取得数据源的KEY
 * @author  liuqinggang
 * @Create Date: 2013-2-26 下午02:51:53
 *
 */
public class DataSourceRouter extends AbstractRoutingDataSource {
	//private final Logger log = Logger.getLogger(DataSourceRouter.class);
    //import org.apache.log4j.Logger;
	// 数据源key的存储控制器
	private DataSourceKey dataSourceKey;

	/**
	 * 获得数据源的key
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		String key = "";
		try {
			key = dataSourceKey.getKey();
		} catch (Throwable e) {
			throw new RuntimeException("get data source key fail", e);
		}
		return key;
	}

	public DataSourceKey getDataSourceKey() {
		return dataSourceKey;
	}

	public void setDataSourceKey(DataSourceKey dataSourceKey) {
		this.dataSourceKey = dataSourceKey;
	}


}
