package com.fairyhawk.service.hessian;

import java.util.Map;

/**
 * @ClassName  FairyhawkHessianServiceSupport
 * @package com.fairyhawk.service.hessian
 * @description hessian接口
 * @author  liuqinggang
 * @Create Date: 2013-3-14 下午5:14:31
 * 
 */
public interface FairyhawkHessianServiceSupport {
	
	public Map<String, Object> getUser(Map<String, String> sourceMap);

}
