package com.fairyhawk.service.hessian;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName  FairyhawkHessianServiceSupportImpl
 * @package com.fairyhawk.service.hessian
 * @description hessian实现
 * @author  liuqinggang
 * @Create Date: 2013-3-14 下午5:15:08
 * 
 */
public class FairyhawkHessianServiceSupportImpl implements
		FairyhawkHessianServiceSupport {

	/* 
	 * 测试hessian
	 * @see com.fairyhawk.service.hessian.FairyhawkHessianServiceSupport#getUser(java.util.Map)
	 */
	@Override
	public Map<String, Object> getUser(Map<String, String> sourceMap) {
		System.out.println("++ 服务端方法 被调用!");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", "fairyhawk");
		return map;
	}

}
