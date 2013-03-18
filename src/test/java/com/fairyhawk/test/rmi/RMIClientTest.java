package com.fairyhawk.test.rmi;

import java.rmi.Naming;

import com.fairyhawk.service.RMIService;

public class RMIClientTest {
	public static void main(String[] args) {
		try {
			 RMIService clinet =(RMIService) Naming.lookup("rmi://localhost:8888/RHello"); 
			 clinet.saysmttosmone("from client 测试");
			 System.out.println("++++ client send ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
