package com.fairyhawk.service.cache;

import java.util.Map;
import java.util.Set;

/**
 * 
 * @ClassName  MemCacheService
 * @package com.fairyhawk.service.cache
 * @description
 * @author  liuqinggang
 * @Create Date: 2013-2-26 下午06:59:26
 *
 */
public interface MemCacheService {
	
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	Object get(String key);
	
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	boolean set(String key, Object value);
	
	
	
	
	
	/**
	 * 批量取
	 * @param keys
	 * @return
	 */
	Map<String, Object> getBulk(Set<String> keys);
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	boolean remove(String key);
	
	
	
	/**
	 * 存,设置超时时间
	 * @param key
	 * @param value
	 * @param exp
	 * @return
	 */
	boolean set(String key, Object value, int exp);
}
