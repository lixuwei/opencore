package com.fairyhawk.common.db;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.PatternMatchUtils;

/**
 * 
 * @ClassName  DataSourceInterceptor
 * @package com.fairyhawk.common.db
 * @description
 * @author  liuqinggang
 * @Create Date: 2013-2-26 下午02:52:06
 *
 */
public class DataSourceInterceptor implements MethodInterceptor {
	// 方法和使用数据源key的对应关系
	private Map<String, String> attributeSource = new HashMap<String, String>();
	// 数据源key的存储控制器
	private DataSourceKey dataSourceKey;

	public Object invoke(MethodInvocation invocation) throws Throwable {
		final String methodName = invocation.getMethod().getName();
		String bestNameMatch = null;
		for (Iterator<String> it = this.attributeSource.keySet().iterator(); it
				.hasNext();) {
			String mappedName = it.next();
			if (isMatch(methodName, mappedName)
					&& (bestNameMatch == null || bestNameMatch.length() <= mappedName
							.length())) {
				bestNameMatch = mappedName;
			}
		}
		String key = attributeSource.get(bestNameMatch);
		if ("READ".equalsIgnoreCase(key)) {
			dataSourceKey.setReadKey();
		} else if ("WRITE".equalsIgnoreCase(key)) {
			dataSourceKey.setWriteKey();
		} else {
			dataSourceKey.setKey(key);
		}
		return invocation.proceed();
	}

	public void setAttributes(Map<String, String> attributeSource) {
		this.attributeSource = attributeSource;
	}

	private boolean isMatch(String methodName, String mappedName) {
		return PatternMatchUtils.simpleMatch(mappedName, methodName);
	}

	public DataSourceKey getDataSourceKey() {
		return dataSourceKey;
	}

	public void setDataSourceKey(DataSourceKey dataSourceKey) {
		this.dataSourceKey = dataSourceKey;
	}

}
