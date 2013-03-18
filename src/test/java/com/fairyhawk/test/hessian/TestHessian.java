package com.fairyhawk.test.hessian;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.fairyhawk.service.hessian.FairyhawkHessianServiceSupport;
import com.fairyhawk.test.Threads;

/**
 * @ClassName  TestHessian
 * @package com.fairyhawk.test.hessian
 * @description
 * @author  liuqinggang
 * @Create Date: 2013-3-14 下午6:26:18
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
public class TestHessian {
	
	@Autowired
	private FairyhawkHessianServiceSupport fairyHessianGateWay;
	
	@Test
	public void testHessian(){
		
		 Map<String, Object> map= fairyHessianGateWay.getUser(null);
		 System.out.println("++++ user:"+map.get("user"));
		 Threads.sleep(1000);
		 map= fairyHessianGateWay.getUser(null);
		 map= fairyHessianGateWay.getUser(null);
		 Threads.sleep(1000);
	}

}
